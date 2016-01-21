package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801215Req;
import com.std.account.dto.res.XN801215Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

public class XN801215 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801215Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doCheckMobile(req.getMobile());
        return new XN801215Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801215Req.class);
        StringValidater.validateBlank(req.getMobile());

    }

}
