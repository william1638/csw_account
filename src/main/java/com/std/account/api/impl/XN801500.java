package com.std.account.api.impl;

import com.std.account.ao.IUserCompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801500Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

public class XN801500 extends AProcessor {
    private IUserCompanyAO userCompanyAO = SpringContextHolder
        .getBean(IUserCompanyAO.class);

    private XN801500Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userCompanyAO.queryUserCompanyListByUserId(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801500Req.class);
        StringValidater.validateBlank(req.getUserId());

    }

}
