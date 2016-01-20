package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801902Req;
import com.std.account.dto.res.XN801902Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 交易密码正确性与否
 * @author: myb858 
 * @since: 2015年11月1日 下午3:45:58 
 * @history:
 */
public class XN801902 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801902Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.checkTradePwd(req.getUserId(), req.getTradePwd());
        return new XN801902Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801902Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId(),
            req.getTradePwd());

    }

}
