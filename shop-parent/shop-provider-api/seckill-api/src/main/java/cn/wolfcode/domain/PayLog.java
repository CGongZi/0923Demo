package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by lanxw
 * 支付日志表
 */
@Setter
@Getter
public class PayLog {
    private String orderNo;//订单编号
    private Date payTime;//支付时间
    private Long totalAmount;//交易数值
}
