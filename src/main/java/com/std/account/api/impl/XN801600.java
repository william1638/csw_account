package com.std.account.api.impl;

import com.std.account.ao.IIdentityAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801600Req;
import com.std.account.dto.res.XN801600Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 人工审批实名认证
 * @author: myb858 
 * @since: 2015年10月27日 下午4:20:56 
 * @history:
 */
public class XN801600 extends AProcessor {

    private IIdentityAO identityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN801600Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        identityAO.doVerifyUserPicture(StringValidater.toLong(req.getId()),
            req.getVerifyUser(), req.getVerifyResult(), req.getRemark());
        return new XN801600Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801600Req.class);
        StringValidater.validateBlank(req.getId(), req.getVerifyUser(),
            req.getVerifyResult());
        StringValidater.validateNumber(req.getId());
    }

}
