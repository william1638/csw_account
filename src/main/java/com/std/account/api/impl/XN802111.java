package com.std.account.api.impl;

import com.std.account.ao.IChargeAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802111Req;
import com.std.account.dto.res.XN802111Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 审批线下充值订单
 * @author: myb858 
 * @since: 2015年8月26日 下午3:44:00 
 * @history:
 */
public class XN802111 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802111Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long fee = StringValidater.toLong(req.getFee());
        chargeAO.doApproveCharge(req.getChargeNo(), req.getApproveUser(),
            req.getApproveResult(), req.getApproveNote(), req.getRefNo(), fee);
        return new XN802111Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802111Req.class);
        StringValidater.validateBlank(req.getChargeNo(), req.getApproveUser(),
            req.getApproveResult(), req.getApproveNote());
    }
}
