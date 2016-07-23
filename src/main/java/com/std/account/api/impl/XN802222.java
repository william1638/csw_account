package com.std.account.api.impl;

import com.std.account.ao.IWithdrawAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802222Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 
 * 审批线下取现订单
 * @author: myb858 
 * @since: 2016年1月13日 下午8:18:05 
 * @history:
 */
public class XN802222 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802222Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        withdrawAO.doApproveWithdraw(req.getWithdrawNo(), req.getApproveUser(),
            req.getApproveResult(), req.getApproveNote(), ECurrency.XNB);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802222Req.class);
        StringValidater.validateBlank(req.getWithdrawNo(),
            req.getApproveUser(), req.getApproveResult(), req.getApproveNote());
    }

}
