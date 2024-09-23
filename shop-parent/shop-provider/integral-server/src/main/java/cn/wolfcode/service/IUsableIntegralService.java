package cn.wolfcode.service;

import cn.wolfcode.domain.OperateIntegralVo;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * Created by lanxw
 */
@LocalTCC
public interface IUsableIntegralService {

    @TwoPhaseBusinessAction(name = "payTry",commitMethod = "payConfirm",rollbackMethod = "payCancel",useTCCFence = true)
    Boolean payTry(@BusinessActionContextParameter("vo") OperateIntegralVo vo, BusinessActionContext context);
    void payConfirm(BusinessActionContext context);
    void payCancel(BusinessActionContext context);
}
