package cn.wolfcode.service.impl;

import cn.wolfcode.domain.OperateIntegralVo;
import cn.wolfcode.mapper.UsableIntegralMapper;
import cn.wolfcode.service.IUsableIntegralService;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lanxw
 */
@Service
public class UsableIntegralServiceImpl implements IUsableIntegralService {
    @Autowired
    private UsableIntegralMapper usableIntegralMapper;

    @Override
    public Boolean payTry(OperateIntegralVo vo, BusinessActionContext context) {
        //判断并修改
        int i = usableIntegralMapper.freezeIntegral(vo.getPhone(), vo.getValue());
        return i>0;
    }

    @Override
    public void payConfirm(BusinessActionContext context) {
        OperateIntegralVo vo = context.getActionContext("vo",OperateIntegralVo.class);
        int i = usableIntegralMapper.commitChange(vo.getPhone(), vo.getValue());
    }

    @Override
    public void payCancel(BusinessActionContext context) {
        OperateIntegralVo vo = context.getActionContext("vo",OperateIntegralVo.class);
        usableIntegralMapper.unFreezeIntegral(vo.getPhone(),vo.getValue());
    }
}
