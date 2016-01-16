package com.std.account.api.impl;

import com.std.account.ao.IUserCompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801351Req;
import com.std.account.dto.res.XN801351Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 用户与企业解绑
 * @author: myb858 
 * @since: 2016年1月14日 下午3:36:24 
 * @history:
 */
public class XN801351 extends AProcessor {
    private IUserCompanyAO userCompanyAO = SpringContextHolder
        .getBean(IUserCompanyAO.class);

    private XN801351Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userCompanyAO.doUnbindUserCompany(req.getUserId(), req.getCompanyId());
        return new XN801351Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801351Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getCompanyId());

    }

}
