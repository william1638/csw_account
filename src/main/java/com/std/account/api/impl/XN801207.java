package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702017Req;
import com.std.account.dto.res.XN702017Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-修改手机号
 * @author: myb858 
 * @since: 2015年9月15日 下午2:36:27 
 * @history:
 */
public class XN801207 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702017Req xn702017Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702017Res(userAO.doChangeMoblie(xn702017Req.getUserId(),
            xn702017Req.getNewMobile(), xn702017Req.getSmsCaptcha(),
            xn702017Req.getTradePwd()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702017Req = JsonUtil.json2Bean(inputparams, XN702017Req.class);
        StringValidater.validateBlank(xn702017Req.getUserId(),
            xn702017Req.getNewMobile(), xn702017Req.getSmsCaptcha(),
            xn702017Req.getTradePwd());
    }

}
