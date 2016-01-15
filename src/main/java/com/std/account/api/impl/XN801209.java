package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801209Req;
import com.std.account.dto.res.XN801209Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-重置登录密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:13:26 
 * @history:
 */
public class XN801209 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801209Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801209Res(userAO.doResetLoginPwd(req.getUserId(),
            req.getOldLoginPwd(), req.getNewLoginPwd()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801209Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getOldLoginPwd(),
            req.getNewLoginPwd());

    }

}
