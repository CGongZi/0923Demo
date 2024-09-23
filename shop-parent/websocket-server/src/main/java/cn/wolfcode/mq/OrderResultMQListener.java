package cn.wolfcode.mq;


import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.concurrent.TimeUnit;

@Component
@RocketMQMessageListener(consumerGroup = "result",topic = MQConstants.ORDER_RESULT_TOPIC,messageModel = MessageModel.BROADCASTING )
public class OrderResultMQListener implements RocketMQListener<OrderMQResult> {
    @SneakyThrows
    @Override
    public void onMessage(OrderMQResult orderMQResult) {
        TimeUnit.MILLISECONDS.sleep(100);
        Session session = wsserver.clients.get(orderMQResult.getToken());
        if (session!=null){
            session.getBasicRemote().sendText(JSON.parse(orderMQResult.getMsg()).toString());
        }
    }
}
