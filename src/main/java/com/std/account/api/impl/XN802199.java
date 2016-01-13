package com.std.account.api.impl;

import com.std.account.ao.ISmsCaptchaAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702499Req;
import com.std.account.dto.res.XN702499Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 发送手机验证码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:09:32 
 * @history:
 */
public class XN802199 extends AProcessor {
    private ISmsCaptchaAO smsCaptchaAO = SpringContextHolder
        .getBean(ISmsCaptchaAO.class);

    private XN702499Req req = null;

    @Override
    public XN702499Res doBusiness() throws BizException {
        boolean flag = smsCaptchaAO.sentSmsCaptcha(req.getMobile(),
            req.getBizType());
        return new XN702499Res(flag);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702499Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getBizType());

    }

}
