package cn.wolfcode.web.service;

import cn.wolfcode.domain.PayVo;
import cn.wolfcode.domain.RefundVo;
import com.alipay.api.AlipayApiException;

import java.util.Map;

public interface IPayService {
    String pay(PayVo vo) throws AlipayApiException;

    Boolean raCheckV1(Map<String, String> param) throws AlipayApiException;

    String reOrder(RefundVo vo) throws AlipayApiException;
}
