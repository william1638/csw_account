package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702985Req;
import com.std.account.dto.res.XN702985Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 交易密码正确性与否
 * @author: myb858 
 * @since: 2015年11月1日 下午3:45:58 
 * @history:
 */
public class XN702985 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702985Req xn702985Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702985Res(userAO.checkTradePwd(xn702985Req.getUserId(),
            xn702985Req.getTradePwd()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702985Req = JsonUtil.json2Bean(inputparams, XN702985Req.class);
        StringValidater.validateBlank(xn702985Req.getTokenId(),
            xn702985Req.getUserId(), xn702985Req.getTradePwd());

    }

}
