package com.std.account.api.impl;

import com.std.account.ao.IIdentityAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702380Req;
import com.std.account.dto.res.XN702380Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 人工审批实名认证
 * @author: myb858 
 * @since: 2015年10月27日 下午4:20:56 
 * @history:
 */
public class XN702380 extends AProcessor {

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN702380Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702380Res(identityAO.doVerifyUserPicture(
            StringValidater.toLong(req.getId()), req.getVerifyUser(),
            req.getVerifyStatus(), req.getRemark()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702380Req.class);
        StringValidater.validateBlank(req.getId(), req.getVerifyUser(),
            req.getVerifyStatus());
        StringValidater.validateNumber(req.getId());
    }

}
