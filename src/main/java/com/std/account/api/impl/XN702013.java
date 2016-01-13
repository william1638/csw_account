package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702013Req;
import com.std.account.dto.res.XN702013Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 *辅助-找回交易密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:14:13 
 * @history:
 */
public class XN702013 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702013Req xn702013Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702013Res(userAO.doFindTradePwd(xn702013Req.getUserId(),
            xn702013Req.getTradePwd(), xn702013Req.getSmsCaptcha(),
            xn702013Req.getIdKind(), xn702013Req.getIdNo()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702013Req = JsonUtil.json2Bean(inputparams, XN702013Req.class);
        StringValidater.validateBlank(xn702013Req.getUserId(),
            xn702013Req.getTradePwd(), xn702013Req.getSmsCaptcha(),
            xn702013Req.getIdKind(), xn702013Req.getIdNo());

    }

}
