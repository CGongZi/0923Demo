package cn.wolfcode.web.feign;

import cn.wolfcode.common.web.Result;
import cn.wolfcode.domain.OperateIntegralVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wolfcode-lanxw
 */
@FeignClient(name = "integral-service",path = "/integral")
public interface IntegralFeignApi {
    @RequestMapping("/pay")
    Result<Boolean> pay(@RequestBody OperateIntegralVo vo);
}
