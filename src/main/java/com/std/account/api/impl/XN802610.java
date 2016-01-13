package com.std.account.api.impl;

import com.std.account.ao.IHLOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702802Req;
import com.std.account.dto.res.XN702802Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 调账申请
 * @author: myb858 
 * @since: 2015年8月26日 下午3:24:07 
 * @history:
 */
public class XN802610 extends AProcessor {
    private IHLOrderAO hlOrderAO = SpringContextHolder
        .getBean(IHLOrderAO.class);

    private XN702802Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        return new XN702802Res(hlOrderAO.doBalance(req.getAccountNumber(),
            amount, req.getApplyUser(), req.getApplyNote()));

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702802Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getApplyUser(), req.getApplyNote());
        StringValidater.validateAmount(req.getAmount());
    }
}
