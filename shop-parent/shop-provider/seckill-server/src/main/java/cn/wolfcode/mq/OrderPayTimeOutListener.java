package cn.wolfcode.mq;

import cn.wolfcode.domain.OrderInfo;
import cn.wolfcode.domain.SeckillProduct;
import cn.wolfcode.mapper.OrderInfoMapper;
import cn.wolfcode.mapper.SeckillProductMapper;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "payTimeOutGroup",topic = MQConstant.ORDER_PAY_TIMEOUT_TOPIC,messageModel = MessageModel.BROADCASTING)
public class OrderPayTimeOutListener implements RocketMQListener<String> {

    @Autowired
    private OrderInfoMapper mapper;

    @Override
    public void onMessage(String orderNo) {
        //检查订单状态
        OrderInfo orderInfo = mapper.find(orderNo);
        if (!orderInfo.getStatus().equals("已支付")){
            //给指定的商品添加库存
        }
    }
}
