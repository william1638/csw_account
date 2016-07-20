package com.std.account.api.impl;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Account;
import com.std.account.dto.req.XN802013Req;
import com.std.account.dto.res.XN802013Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 根据用户编号和币种查询账户信息(oss)
 * @author: xieyj 
 * @since: 2016年7月21日 上午12:02:14 
 * @history:
 */
public class XN802013 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802013Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Account account = accountAO.getAccountByUser(req.getUserId(),
            req.getCurrency());
        XN802013Res res = new XN802013Res();
        if (account != null) {
            res.setUserId(account.getUserId());
            res.setAccountNumber(account.getAccountNumber());
            res.setStatus(account.getStatus());
            res.setAmount(account.getAmount());
            res.setFrozenAmount(account.getFrozenAmount());
            res.setCurrency(account.getCurrency());
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802013Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getCurrency());
    }
}
