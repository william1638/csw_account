package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PhoneUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801200Req;
import com.std.account.dto.res.XN801200Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 注册
 * @author: myb858 
 * @since: 2015年8月23日 上午11:42:00 
 * @history:
 */
public class XN801200 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801200Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801200Res(userAO.doRegister(req.getMobile(),
            req.getSmsCaptcha(), req.getLoginPwd(), req.getRegisterIp(),
            req.getUserReferee()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801200Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getSmsCaptcha(),
            req.getLoginPwd(), req.getRegisterIp());
        PhoneUtil.checkMobile(req.getMobile());// // 判断格式
    }
}
