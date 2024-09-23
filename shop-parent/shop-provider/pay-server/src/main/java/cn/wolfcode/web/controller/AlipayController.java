package cn.wolfcode.web.controller;

import cn.wolfcode.common.web.Result;
import cn.wolfcode.config.AlipayProperties;
import cn.wolfcode.domain.PayVo;
import cn.wolfcode.domain.RefundVo;
import cn.wolfcode.web.service.IPayService;
import cn.wolfcode.web.service.impl.PayServiceImpl;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lanxw
 */
@RestController
@RequestMapping("/alipay")
public class AlipayController {
    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private IPayService payService;

    /**
     * 支付操作
     * @param vo
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping ("/pay")
    public Result<String> payController(@RequestBody PayVo vo) throws AlipayApiException {
        String html = payService.pay(vo);
        return Result.success(html);
    }

    /**
     * 验签操作
     * @param param
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping("rsaCheckV1")
    public Result<Boolean> rsaCheckV1(@RequestParam Map<String,String> param) throws AlipayApiException {
        Boolean flag = payService.raCheckV1(param);
        return Result.success(flag);

    }
    @RequestMapping("reOrder")
    public Result<String> reOrder(@RequestBody RefundVo vo) throws AlipayApiException {
        //判断退款类型为积分还是在线
        String orderType = "在线退款";

        String flag = payService.reOrder(vo);
        return Result.success(flag);

    }

}
