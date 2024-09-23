package cn.wolfcode.mq;

import cn.wolfcode.service.IOrderInfoService;
import cn.wolfcode.service.impl.SeckillProductServiceImpl;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "orderCreate",topic = MQConstant.STOCK_OVER_FLAG_TOPIC,messageModel = MessageModel.BROADCASTING)
public class OrderOverFlagMessageListener implements RocketMQListener<Long> {


    @Override
    public void onMessage(Long id) {
        SeckillProductServiceImpl.booleanConcurrentHashMap.put(id,true);
    }
}
