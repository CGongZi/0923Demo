package cn.wolfcode.mq;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@RocketMQTransactionListener
@Component
public class OrderRefundTranstationalListener implements RocketMQLocalTransactionListener {
    //本地事务执行方法
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            String orderNo= (String) arg;
            //正常处理逻辑成功
//            return RocketMQLocalTransactionState.COMMIT;
            return RocketMQLocalTransactionState.UNKNOWN;
        }catch (Exception e){
            //处理失败
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
    //本地事务回查方法
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        //查询 订单状态返回commit/rollback
        return RocketMQLocalTransactionState.COMMIT;
    }
}
