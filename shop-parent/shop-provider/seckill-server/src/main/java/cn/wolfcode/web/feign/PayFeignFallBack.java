package cn.wolfcode.web.feign;

import cn.wolfcode.common.web.Result;
import cn.wolfcode.domain.PayVo;
import cn.wolfcode.domain.RefundVo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Component
public class PayFeignFallBack implements PayFeignApi{

    //降级操作

    @Override
    public Result<String> pay(PayVo payVo) {
        return null;
    }

    @Override
    public Result<Boolean> rsaCheckV1(Map<String, String> param) {
        return null;
    }

    @Override
    public Result<String> reOrder(RefundVo vo) {
        return null;
    }
}
