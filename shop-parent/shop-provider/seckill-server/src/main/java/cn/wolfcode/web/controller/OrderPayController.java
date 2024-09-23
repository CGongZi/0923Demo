package cn.wolfcode.web.controller;


import cn.wolfcode.common.web.Result;
import cn.wolfcode.domain.OrderInfo;
import cn.wolfcode.service.IOrderInfoService;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by lanxw
 */
@RestController
@RequestMapping("/orderPay")
public class OrderPayController {
    @Autowired
    private IOrderInfoService orderInfoService;

    @Value("${pay.truePage}")
    private String truePage;
    @Value("${pay.errorPage}")
    private String errorPage;

    @RequestMapping("orderPay")
    public Result<String> orderPay(String orderNo,Integer type){
        String res = orderInfoService.orderPay(orderNo,type);
        return Result.success(res);
    }

    //异步回调地址 公网映射本机地址/seckill/alipay/notifyUrl
    @RequestMapping("/notifyUrl")
    public String notifyUrl(@RequestParam Map<String,String> param){
        orderInfoService.notifyPay(param);
        return "success";
    }

    //同步回调地址 公网映射本机地址/seckill/alipay/returnUrl
    @RequestMapping("/returnUrl")
    public void returnUrl(@RequestParam Map<String,String> param,HttpServletResponse response) throws IOException {
        try{
            String orderNo = orderInfoService.returnUrl(param);
            response.sendRedirect(truePage);
        }catch (Exception e){
            response.sendRedirect(errorPage);
        }
    }

    @RequestMapping("reOrder")
    public Result<String> reOrder(String orderNo) throws AlipayApiException {
        //判断退款类型为积分还是在线
        String orderType = "在线退款";

        String flag = orderInfoService.reOrder(orderNo);
        return Result.success(flag);

    }
}
