package cn.wolfcode.service;

import cn.wolfcode.domain.SeckillProduct;

import java.util.List;

/**
 * Created by lanxw
 */
public interface ISeckillProductService {
    List<SeckillProduct> queryByTime(Integer time);
    SeckillProduct find(Long seckillId);
    int decrStock(Long seckillId);
    void incrStockCount(Long seckillId);
}
