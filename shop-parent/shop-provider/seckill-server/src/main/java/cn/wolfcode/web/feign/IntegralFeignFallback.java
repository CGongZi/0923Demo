package cn.wolfcode.web.feign;

import cn.wolfcode.common.web.Result;
import cn.wolfcode.domain.OperateIntegralVo;
import org.springframework.stereotype.Component;

/**
 * Created by wolfcode-lanxw
 */
@Component
public class IntegralFeignFallback implements IntegralFeignApi {
    @Override
    public Result<Boolean> pay(OperateIntegralVo vo) {
        return null;
    }
}


