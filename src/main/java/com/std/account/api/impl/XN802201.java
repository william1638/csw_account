package com.std.account.api.impl;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802201Req;
import com.std.account.dto.res.XN802201Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 线下取现
 * @author: myb858 
 * @since: 2015年8月23日 下午4:43:48 
 * @history:
 */
public class XN802201 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN802201Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        return new XN802201Res(cqOrderAO.doWithdrawOffline(
            req.getAccountNumber(), amount, req.getBankCode(),
            req.getSubbranch(), req.getBankcardNo(), req.getTradePwd()));

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802201Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getBankCode(), req.getBankcardNo(), req.getTradePwd());
        StringValidater.validateAmount(req.getAmount());
    }
}
