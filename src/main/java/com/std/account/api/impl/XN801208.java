package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801208Req;
import com.std.account.dto.res.XN801208Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-找回登录密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:11:01 
 * @history:
 */
public class XN801208 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801208Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801208Res(userAO.doFindLoginPwd(req.getMobile(),
            req.getNewLoginPwd(), req.getSmsCaptcha()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801208Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getSmsCaptcha(),
            req.getNewLoginPwd());

    }

}
