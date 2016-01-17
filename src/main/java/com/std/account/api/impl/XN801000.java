package com.std.account.api.impl;

import com.std.account.ao.ISmsOutAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PhoneUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801100Req;
import com.std.account.dto.res.XN801100Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 发送短信验证码
 * @author: myb858 
 * @since: 2016年1月16日 下午9:02:25 
 * @history:
 */
public class XN801000 extends AProcessor {
    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN801100Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        smsOutAO.sendCaptcha(req.getMobile(), req.getBizType());
        return new XN801100Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801100Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getBizType());
        PhoneUtil.checkMobile(req.getMobile());// 判断格式
    }

}
