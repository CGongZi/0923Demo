package cn.wolfcode.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by lanxw
 */
public interface UsableIntegralMapper {
    /**
     * 冻结用户积分金额
     * @param phone
     * @param amount
     * @return
     */
    int freezeIntegral(@Param("phone") String phone, @Param("amount") Long amount);

    /**
     * 提交改变，冻结金额真实扣除
     * @param phone
     * @param amount
     * @return
     */
    int commitChange(@Param("phone") String phone, @Param("amount") Long amount);

    /**
     * 取消冻结金额
     * @param phone
     * @param amount
     */
    void unFreezeIntegral(@Param("phone") String phone, @Param("amount") Long amount);

    /**
     * 增加积分
     * @param phone
     * @param amount
     */
    void incrIntegral(@Param("phone") String phone, @Param("amount") Long amount);
    /**
     * 减少积分
     * @param phone
     * @param amount
     */
    int substractIntegral(@Param("phone") String phone, @Param("amount") Long amount);
}
