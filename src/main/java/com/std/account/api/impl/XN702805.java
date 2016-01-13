package com.std.account.api.impl;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702805Req;
import com.std.account.dto.res.XN702805Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 线下充值订单录入
 * @author: myb858 
 * @since: 2015年12月2日 下午4:02:34 
 * @history:
 */
public class XN702805 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN702805Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        return new XN702805Res(cqOrderAO.doChargeOffline(
            req.getAccountNumber(), amount, req.getBankCode(),
            req.getBankcardNo()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702805Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getBankCode(), req.getBankcardNo());
        StringValidater.validateAmount(req.getAmount());

    }

}
