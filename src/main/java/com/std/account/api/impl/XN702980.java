package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702980Req;
import com.std.account.dto.res.XN702980Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 拿tokenId的一步，在登录阶段调用
 * @author: myb858 
 * @since: 2015年10月28日 上午10:31:58 
 * @history:
 */
public class XN702980 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702980Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = userAO.doLogin(req.getLoginName(), req.getLoginPwd(),
            "0", "");
        XN702980Res res = new XN702980Res();
        res.setTokenId(userId);
        res.setUserId(userId);
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702980Req.class);
        StringValidater.validateBlank(req.getLoginName(), req.getLoginPwd());
    }

}
