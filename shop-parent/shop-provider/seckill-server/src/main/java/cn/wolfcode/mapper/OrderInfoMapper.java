package cn.wolfcode.mapper;

import cn.wolfcode.domain.OrderInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wolfcode-lanxw
 */
public interface OrderInfoMapper {
    /**
     * 插入订单信息
     * @param orderInfo
     * @return
     */
    int insert(OrderInfo orderInfo);

    /**
     * 根据订单编号查找订单
     * @param orderNo
     * @return
     */
    OrderInfo find(String orderNo);

    /**
     * 将订单状态修改成指定状态
     * @param orderNo
     * @param toStatus
     * @param fromStatus
     * @return
     */
    int changeOrderStatus(@Param("orderNo") String orderNo, @Param("toStatus") Integer toStatus,@Param("fromStatus")Integer fromStatus);

    /**
     * 将订单状态修改成支付状态
     * @param orderNo
     * @param toStatus
     * @param fromStatus
     * @param payType
     * @return
     */
    int changeOrderToPayStatus(@Param("orderNo") String orderNo, @Param("toStatus") Integer toStatus,@Param("fromStatus")Integer fromStatus, @Param("payType") int payType);

    OrderInfo getByPhoneAndSecKillId(@Param("userPhone") String userPhone, @Param("seckillId") Long seckillId);
}
