package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PhoneUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702001Req;
import com.std.account.dto.res.XN702001Res;
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

    private XN702001Req xn702001Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702001Res(userAO.doRegister(xn702001Req.getMobile(),
            xn702001Req.getLoginPwd(), xn702001Req.getRegisterIp(),
            xn702001Req.getUserReferee(), xn702001Req.getSmsCaptcha()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702001Req = JsonUtil.json2Bean(inputparams, XN702001Req.class);
        StringValidater.validateBlank(xn702001Req.getMobile(),
            xn702001Req.getLoginPwd(), xn702001Req.getRegisterIp(),
            xn702001Req.getSmsCaptcha());
        // 判断格式
        PhoneUtil.checkMobile(xn702001Req.getMobile());
    }
}
