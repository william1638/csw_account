package com.std.account.api.impl;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Account;
import com.std.account.dto.req.XN702600Req;
import com.std.account.dto.res.XN702600Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 账户信息查询
 * @author: myb858 
 * @since: 2015年8月23日 下午2:59:32 
 * @history:
 */
public class XN802400 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN702600Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Account account = accountAO.getAccount(req.getUserId());
        XN702600Res res = new XN702600Res();
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
        req = JsonUtil.json2Bean(inputparams, XN702600Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
