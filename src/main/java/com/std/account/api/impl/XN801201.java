package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PhoneUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702002Req;
import com.std.account.dto.res.XN702002Res;
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

    private XN702002Req xn702002Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702002Res(userAO.doLogin(xn702002Req.getLoginName(),
            xn702002Req.getLoginPwd(), xn702002Req.getLoginType(),
            xn702002Req.getLoginIp()));

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702002Req = JsonUtil.json2Bean(inputparams, XN702002Req.class);
        StringValidater.validateBlank(xn702002Req.getLoginName(),
            xn702002Req.getLoginPwd(), xn702002Req.getLoginType());
        // 当loginType=1时，loginIp必填；当loginType！=1时，loginIp选填
        if (EBoolean.YES.getCode().equalsIgnoreCase(xn702002Req.getLoginType())) {
            StringValidater.validateBlank(xn702002Req.getLoginIp());
        }
        // 判断格式
        PhoneUtil.checkMobile(xn702002Req.getLoginName());
    }
}
