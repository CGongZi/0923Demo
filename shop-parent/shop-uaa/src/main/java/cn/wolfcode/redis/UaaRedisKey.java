package cn.wolfcode.redis;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * Created by wolfcode-lanxw
 */
@Getter
public enum UaaRedisKey {
    USERLOGIN_STRING("userLogin:%s",TimeUnit.DAYS,7),
    USERINFO_STRING("userInfo:%s",TimeUnit.DAYS,7);
    UaaRedisKey(String prefix){
        this .prefix = prefix;
    }
    UaaRedisKey(String prefix, TimeUnit unit, int expireTime){
        this.prefix = prefix;
        this.unit = unit;
        this.expireTime = expireTime;
    }
    public String getRealKey(String... keys){
        return String.format(this.prefix,keys);
    }
    private String prefix;
    private TimeUnit unit;
    private int expireTime;
}
