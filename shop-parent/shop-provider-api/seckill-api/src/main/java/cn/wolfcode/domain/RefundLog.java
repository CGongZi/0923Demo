package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lanxw
 * 退款时需要记录日志
 */
@Setter@Getter
public class RefundLog implements Serializable {
    private String orderNo;//商品订单号
    private Long refundAmount;//退款金额退款积分
    private String refundReason;//退款原因
    private Date refundTime;//退款时间
}
