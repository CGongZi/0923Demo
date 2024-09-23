package cn.wolfcode.service.impl;

import cn.wolfcode.common.constants.CommonConstants;
import cn.wolfcode.common.exception.BusinessException;
import cn.wolfcode.common.web.CommonCodeMsg;
import cn.wolfcode.common.web.Result;
import cn.wolfcode.domain.*;
import cn.wolfcode.mapper.OrderInfoMapper;
import cn.wolfcode.mapper.PayLogMapper;
import cn.wolfcode.mapper.RefundLogMapper;
import cn.wolfcode.mq.MQConstant;
import cn.wolfcode.mq.OrderMessage;
import cn.wolfcode.redis.SeckillRedisKey;
import cn.wolfcode.service.IOrderInfoService;
import cn.wolfcode.service.ISeckillProductService;
import cn.wolfcode.util.DateUtil;
import cn.wolfcode.util.IdGenerateUtil;
import cn.wolfcode.web.feign.IntegralFeignApi;
import cn.wolfcode.web.feign.PayFeignApi;
import cn.wolfcode.web.msg.SeckillCodeMsg;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by wolfcode-lanxw
 */
@Service
public class OrderInfoSeviceImpl implements IOrderInfoService {
    @Autowired
    private ISeckillProductService seckillProductService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private PayLogMapper payLogMapper;
    @Autowired
    private RefundLogMapper refundLogMapper;

    @Value("${pay.notUrl}")
    private String notUrl;

    @Value("${pay.retUrl}")
    private String retUrl;


    @Qualifier("cn.wolfcode.web.feign.PayFeignApi")
    @Autowired
    private PayFeignApi payFeignApi;

    @Override
    public String createOrder(Long seckillId, String userPhone) {
        return null;
    }

    @Override
    public String orderPay(String orderNo, Integer type) {
        OrderInfo orderInfo = orderInfoMapper.find(orderNo);
        if (orderInfo==null){
            return null;
        }
        if (!orderInfo.getStatus().equals(OrderInfo.STATUS_UNPAID)){
            return null;
        }
        PayVo payVo = new PayVo();
        payVo.setBody(orderInfo.getProductName());
        payVo.setNotifyUrl(notUrl);
        payVo.setReturnUrl(retUrl);
        Result<String> pay = payFeignApi.pay(payVo);

        return pay.getData();

    }

    @Override
    public void notifyPay(Map<String, String> param) {
        //1.进行验签操作
        Result<Boolean> stringResult = payFeignApi.rsaCheckV1(param);

        if (stringResult==null||stringResult.hasError()||!stringResult.getData()){
            return;
        }

        String orderNo = param.get("orderNo");
        int i = orderInfoMapper.changeOrderStatus(orderNo, OrderInfo.STATUS_ACCOUNT_PAID, OrderInfo.STATUS_UNPAID);
        if (i==0){
            //说明其他线程以对此订单操作过
            //执行退款操作

        }
    }

    @Override
    public String returnUrl(Map<String, String> param) {
        //1.进行验签操作
        Result<Boolean> stringResult = payFeignApi.rsaCheckV1(param);

        if (stringResult==null||stringResult.hasError()||!stringResult.getData()){
            return "";
        }

        String orderNo = param.get("orderNo");
        return orderNo;
    }

    @Override
    public String reOrder(String orderNo) {
        RefundVo refundVo = new RefundVo();
        String result = payFeignApi.reOrder(refundVo).getData();
        if (Objects.equals(result, "")){
            return "退款异常";
        }
        //将订单状态改为已退款
        return "";
    }

    /**
     * 测试同步事务消息
     * 1.发送事务消息接收，监听器处理正常的业务逻辑，若失败
     * @param orderNO
     * @return
     */
    public String  refund(String orderNO){
        RefundVo vo = new RefundVo();
        vo.setOutTradeNo(orderNO);
        Message<RefundVo> build = MessageBuilder.withPayload(vo).build();
        /**
         * 发送同步事务消息
         * 1.发送消息给中间件
         * 2.得到存储结果 执行后续流程
         */
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction("tx_topic", build, orderNO);
        if (result.getLocalTransactionState().equals(LocalTransactionState.COMMIT_MESSAGE)){
            return "退款成功";
        } else if (result.getLocalTransactionState().equals(LocalTransactionState.ROLLBACK_MESSAGE)) {
            return "退款失败";
        }else {
            return "未知状态";
        }

    }
}
