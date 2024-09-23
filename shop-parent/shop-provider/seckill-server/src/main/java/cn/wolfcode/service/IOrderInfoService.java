package cn.wolfcode.service;


import cn.wolfcode.domain.OrderInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by wolfcode-lanxw
 */
public interface IOrderInfoService {
    String createOrder(Long seckillId, String userPhone);

    String orderPay(String orderNo, Integer type);

    void notifyPay(Map<String, String> param);

    String returnUrl(Map<String, String> param);

    String reOrder(String orderNo);
}
