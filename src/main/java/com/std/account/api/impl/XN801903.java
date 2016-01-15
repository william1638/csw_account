package com.std.account.api.impl;

import com.std.account.ao.ISmsOutAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801903Req;
import com.std.account.dto.res.XN801903Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 应用委托发送短信（因为应用本身只能拿到登录名，其他信息如手机号理论上拿不到的）
 * @author: myb858 
 * @since: 2015年11月10日 上午9:18:50 
 * @history:
 */
public class XN801903 extends AProcessor {
    private ISmsOutAO smsOutAO = SpringContextHolder.getBean(ISmsOutAO.class);

    private XN801903Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801903Res(smsOutAO.sendAppSms(req.getUserId(),
            req.getContent()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801903Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId(),
            req.getContent());

    }

}
