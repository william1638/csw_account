package com.std.account.api.impl;

import com.std.account.ao.IChargeAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802121Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 审批线下申请积分
 * @author: xieyj 
 * @since: 2016年7月23日 上午6:56:32 
 * @history:
 */
public class XN802121 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802121Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        chargeAO.doApproveCharge(req.getChargeNo(), req.getApproveUser(),
            req.getApproveResult(), req.getApproveNote(), null, null,
            ECurrency.XNB);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802121Req.class);
        StringValidater.validateBlank(req.getChargeNo(), req.getApproveUser(),
            req.getApproveResult());
    }
}
