package cn.wolfcode.mq;

import cn.wolfcode.service.IOrderInfoService;
import cn.wolfcode.service.impl.SeckillProductServiceImpl;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "orderCreate",topic = MQConstant.ORDER_CREATE_TOPIC)
public class OrderCreateMessageListener implements RocketMQListener<OrderMessage> {
    @Autowired
    private IOrderInfoService service;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(OrderMessage orderMessage) {
        OrderMQResult orderMQResult = new OrderMQResult();
        try {
            String orderNo = service.createOrder(orderMessage.getSeckillId(),orderMessage.getUserPhone());
            orderMQResult = new OrderMQResult();
            //发送成功后 发送延时消息 指定时间后判断订单是否已支付
            Message<String> message = MessageBuilder.withPayload(orderNo).build();
            rocketMQTemplate.syncSend(MQConstant.ORDER_PAY_TIMEOUT_TOPIC,message,MQConstant.MESSAGE_SEND_TIMEOUT,13);

        }catch (Exception e){
            //回补状态操作

        }


        SendResult sendResult = rocketMQTemplate.syncSend(MQConstant.ORDER_RESULT_TOPIC, orderMQResult);
    }
}
