package com.std.account.api.impl;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702987Req;
import com.std.account.dto.res.XN702987Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 解冻/冻结（第一步）
 * @author: myb858 
 * @since: 2015年11月18日 下午1:17:16 
 * @history:
 */
public class XN702987 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN702987Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        String userId = req.getUserId();
        accountAO.doFreeze(userId, StringValidater.toLong(req.getAmount()),
            req.getOppositeSystem(), req.getOppositeAccount(), req.getRemark());
        XN702987Res res = new XN702987Res();
        res.setUserId(userId);
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702987Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId(),
            req.getOppositeSystem());
        StringValidater.validateAmount(req.getAmount());
    }

}
