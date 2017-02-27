package com.std.account.api.impl;

import com.std.account.ao.IJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802519Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 兑换审批
 * @author: xieyj 
 * @since: 2016年12月25日 下午3:14:31 
 * @history:
 */
public class XN802519 extends AProcessor {

    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN802519Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        Double rate = StringValidater.toDouble(req.getRate());
        jourAO.approveExchangeAmount(req.getSystemCode(), req.getCode(), rate,
            req.getApproveResult(), req.getApprover(), req.getApproveNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802519Req.class);
        StringValidater.validateBlank(req.getSystemCode(), req.getCode(),
            req.getRate(), req.getApproveResult(), req.getApprover(),
            req.getApproveNote());
    }
}
