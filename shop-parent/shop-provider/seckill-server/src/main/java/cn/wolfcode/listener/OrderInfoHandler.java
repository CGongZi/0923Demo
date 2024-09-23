package cn.wolfcode.listener;

import cn.wolfcode.domain.OrderInfo;
import cn.wolfcode.domain.PayVo;
import cn.wolfcode.mq.MQConstant;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * 针对某一张表bin_log的增删改读取到数据 并处理对应操作 来达到数据同步的操作
 */
@Component
@CanalTable("table_user")
public class OrderInfoHandler implements EntryHandler<OrderInfo> {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void insert(OrderInfo payVo) {
        //在redis中存入key防止用户重复下单
        redisTemplate.opsForSet().add(payVo.getOrderNo(),"phone");
    }

    /**
     * 保存的修改前后的数据  根据差异进行操作
     * @param before
     * @param after
     */
    @Override
    public void update(OrderInfo before, OrderInfo after) {
        if (before.getStatus().equals(OrderInfo.STATUS_UNPAID)&&after.getStatus().equals(OrderInfo.STATUS_TIMEOUT)){
            EntryHandler.super.update(before, after);
            //针对用户订单超时状态 检测到状态更改 将库存回补 将redis中的库存一并回补
            redisTemplate.opsForValue().increment("orderId");
            rocketMQTemplate.syncSend(MQConstant.STOCK_OVER_FLAG_TOPIC,after.getSeckillId());
        }

    }

    @Override
    public void delete(OrderInfo payVo) {
        //在操作撤销时 数据删除 也一并将redis中的数据删除掉 保证数据一致
        //解决了 Transactional 中操作redis 回退时 redis中数据不一致的问题
        EntryHandler.super.delete(payVo);
        redisTemplate.opsForSet().remove(payVo.getOrderNo(),"phone");
    }
}
