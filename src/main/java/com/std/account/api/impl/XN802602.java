package com.std.account.api.impl;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802602Req;
import com.std.account.dto.res.XN802602Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 线下取现订单录入
 * @author: myb858 
 * @since: 2016年1月13日 下午8:27:20 
 * @history:
 */
public class XN802602 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN802602Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        return new XN802602Res(cqOrderAO.doWithdrawOSS(req.getAccountNumber(),
            amount, req.getBankCode(), req.getBankcardNo(), req.getSubbranch()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802602Req.class);
        StringValidater.validateBlank(req.getAccountNumber(), req.getAmount(),
            req.getBankCode(), req.getBankcardNo());
        StringValidater.validateAmount(req.getAmount());

    }

}
