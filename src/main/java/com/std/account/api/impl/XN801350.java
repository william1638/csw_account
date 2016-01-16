package com.std.account.api.impl;

import com.std.account.ao.IUserCompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801350Req;
import com.std.account.dto.res.XN801350Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 用户与企业绑定
 * @author: myb858 
 * @since: 2016年1月14日 下午3:34:37 
 * @history:
 */
public class XN801350 extends AProcessor {
    private IUserCompanyAO userCompanyAO = SpringContextHolder
        .getBean(IUserCompanyAO.class);

    private XN801350Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userCompanyAO.doBindUserCompany(req.getUserId(), req.getCompanyId(),
            req.getRemark());
        return new XN801350Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801350Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getCompanyId());
    }

}
