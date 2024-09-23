package cn.wolfcode.service.impl;

import cn.wolfcode.common.exception.BusinessException;
import cn.wolfcode.common.web.CommonCodeMsg;
import cn.wolfcode.domain.SeckillProduct;
import cn.wolfcode.mapper.SeckillProductMapper;
import cn.wolfcode.mq.MQConstant;
import cn.wolfcode.mq.OrderMessage;
import cn.wolfcode.redis.SeckillRedisKey;
import cn.wolfcode.service.ISeckillProductService;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.Buffer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by lanxw
 */
@Service
public class SeckillProductServiceImpl implements ISeckillProductService {
    @Autowired
    private SeckillProductMapper seckillProductMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static ConcurrentHashMap<Long,Boolean> booleanConcurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public List<SeckillProduct> queryByTime(Integer time) {
        String key = SeckillRedisKey.SECKILL_PRODUCT_LIST.getRealKey(time.toString());
        List<String> idList = redisTemplate.opsForList().range(key, 0, -1);
        if(idList.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        List<String> keyList = idList.stream().map(id -> SeckillRedisKey.SECKILL_PRODUCT_DETAIL.getRealKey(id)).collect(Collectors.toList());
        List<SeckillProduct> seckillProductList = redisTemplate.opsForValue().multiGet(keyList).stream().filter(objStr -> objStr != null).map(objStr -> JSON.parseObject(objStr, SeckillProduct.class)).collect(Collectors.toList());
        return seckillProductList;
    }

    @Override
    public SeckillProduct find( Long seckillId) {
        String key = SeckillRedisKey.SECKILL_PRODUCT_DETAIL.getRealKey(seckillId.toString());
        String objStr = redisTemplate.opsForValue().get(key);
        if(objStr==null){
            throw new BusinessException(CommonCodeMsg.ILLEGAL_OPERATION);
        }
        return JSON.parseObject(objStr,SeckillProduct.class);
    }

    @Override
    public int decrStock(Long seckillId) {
        return seckillProductMapper.decrStock(seckillId);
    }

    @Override
    public void incrStockCount(Long seckillId) {
        seckillProductMapper.incrStock(seckillId);
    }

    public void doSeckill(Long id,String phone,String token){
        SeckillProduct seckillProduct = seckillProductMapper.find(id);
        String realKey = SeckillRedisKey.SECKILL_ORDER_SET.getRealKey(id.toString());
        if (booleanConcurrentHashMap!=null && booleanConcurrentHashMap.get(id)){
            throw new RuntimeException("已抢购光");
        }

        //2.判断用户是否下过单
        if (Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(realKey, phone))){
            throw new RuntimeException("已抢购过");
        }
        //3.不要出现超卖少卖情况
        String reaCountKey = SeckillRedisKey.SECKILL_PRODUCT_STOCK.getRealKey(id.toString());
        Long decrement = redisTemplate.opsForValue().decrement(reaCountKey);
        if (decrement!=null && decrement<0){
            //保持redis库存为0
            booleanConcurrentHashMap.put(id,false);
            redisTemplate.opsForValue().increment(reaCountKey);
            throw new RuntimeException("已抢购光");
        }

        OrderMessage orderMessage = new OrderMessage(id,token,phone);
        SendResult sendResult = rocketMQTemplate.syncSend(MQConstant.ORDER_CREATE_TOPIC, orderMessage);
        if (!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
            redisTemplate.opsForValue().increment(reaCountKey);
            rocketMQTemplate.syncSend(MQConstant.STOCK_OVER_FLAG_TOPIC,id);
            throw new RuntimeException("已抢购光");
        }


//        int count = seckillProductMapper.decrStock(id);
//        if (count==0){
//            throw new RuntimeException("已抢购光");
//        }
//
//        try {
//            String orderNo = seckillProductMapper.creatOrder(id,phone);
//        }catch (Exception e){
//            redisTemplate.opsForValue().increment(reaCountKey);
//            throw e;
//        }



    }
}
