package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PhoneUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801201Req;
import com.std.account.dto.res.XN801201Res;
import com.std.account.enums.EBoolean;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 登录
 * @author: myb858 
 * @since: 2015年8月23日 下午1:39:09 
 * @history:
 */
public class XN801201 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801201Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801201Res(userAO.doLogin(req.getLoginName(),
            req.getLoginPwd(), req.getLoginType(), req.getLoginIp()));

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801201Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getLoginPwd(),
            req.getLoginType());
        // 当loginType=1时，loginIp必填；当loginType！=1时，loginIp选填
        if (EBoolean.YES.getCode().equalsIgnoreCase(req.getLoginType())) {
            StringValidater.validateBlank(req.getLoginIp());
        }
        // 判断格式
        PhoneUtil.checkMobile(req.getLoginName());
    }
}
