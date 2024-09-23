package cn.wolfcode.redis;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * Created by wolfcode-lanxw
 */
@Getter
public enum SeckillRedisKey {
    SECKILL_PRODUCT_DETAIL("seckillProductDetail:%s",TimeUnit.HOURS,24),
    SECKILL_PRODUCT_STOCK("seckillProductStock:%s",TimeUnit.HOURS,24),
    SECKILL_PRODUCT_LIST("seckillProductList:%s"),
    SECKILL_ORDER_LOCK("seckillOrderLock:%s:%s"),
    SECKILL_ORDER_SET("seckillOrderSet:%s");
    SeckillRedisKey(String prefix, TimeUnit unit, int expireTime){
        this.prefix = prefix;
        this.unit = unit;
        this.expireTime = expireTime;
    }
    SeckillRedisKey(String prefix){
        this.prefix = prefix;
    }
    public String getRealKey(String... keys){
        return String.format(this.prefix,keys);
    }
    private String prefix;
    private TimeUnit unit;
    private int expireTime;
}
