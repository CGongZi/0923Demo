package cn.wolfcode.web.feign;

import cn.wolfcode.common.web.Result;
import cn.wolfcode.domain.PayVo;
import cn.wolfcode.domain.RefundVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "pay-service",path = "alipay",fallback = PayFeignFallBack.class)
public interface PayFeignApi {

    @RequestMapping("/pay")
    Result<String> pay(@RequestBody PayVo payVo);

    @RequestMapping("/rsaCheckV1")
    Result<Boolean> rsaCheckV1(@RequestParam Map<String,String> param);

    @RequestMapping("/reOrder")
    Result<String> reOrder(@RequestBody RefundVo vo);
}
