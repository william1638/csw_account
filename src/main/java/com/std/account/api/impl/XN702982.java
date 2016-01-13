package com.std.account.api.impl;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Account;
import com.std.account.dto.req.XN702982Req;
import com.std.account.dto.res.XN702982Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 获取账户信息
 * @author: myb858 
 * @since: 2015年11月1日 下午2:56:49 
 * @history:
 */
public class XN702982 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN702982Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = req.getUserId();
        Account account = accountAO.getAccount(userId);
        XN702982Res res = new XN702982Res();
        if (account != null) {
            res.setUserId(userId);
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
        req = JsonUtil.json2Bean(inputparams, XN702982Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId());

    }
}
