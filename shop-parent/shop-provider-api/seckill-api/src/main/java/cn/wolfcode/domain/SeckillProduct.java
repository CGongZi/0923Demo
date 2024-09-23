package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lanxw
 * 秒杀商品表
 */
@Setter@Getter
public class SeckillProduct {
    private Long id;//秒杀商品ID
    private String productName;//商品名称
    private String productTitle;//商品标题
    private String productImg;//商品图片
    private String productDetail;//商品详情
    private BigDecimal productPrice;//商品价格
    private Integer currentCount;//当前库存数量
    private BigDecimal seckillPrice;//秒杀价格
    private Long integral;//支付积分
    private Integer stockCount;//库存总数
    private Date startDate;//秒杀日期
    private Integer time;//秒杀场次

}
