package com.std.account.api.impl;

import com.std.account.ao.IHLOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802710Req;
import com.std.account.dto.res.XN802710Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 人工干预账户申请(oss)
 * @author: myb858 
 * @since: 2015年8月26日 下午3:24:07 
 * @history:
 */
public class XN802710 extends AProcessor {
    private IHLOrderAO hlOrderAO = SpringContextHolder
        .getBean(IHLOrderAO.class);

    private XN802710Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        return new XN802710Res(hlOrderAO.doBalance(req.getAccountNumber(),
            req.getDirection(), amount, req.getApplyUser(), req.getApplyNote()));

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802710Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getDirection(), req.getAmount(), req.getApplyUser(),
            req.getApplyNote());
        StringValidater.validateAmount(req.getAmount());
    }
}
