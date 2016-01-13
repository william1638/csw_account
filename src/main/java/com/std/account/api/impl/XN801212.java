package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702014Req;
import com.std.account.dto.res.XN702014Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-重置交易密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:30:11 
 * @history:
 */
public class XN801212 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702014Req xn702014Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702014Res(userAO.doResetTradePwd(xn702014Req.getUserId(),
            xn702014Req.getOldTradePwd(), xn702014Req.getNewTradePwd()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702014Req = JsonUtil.json2Bean(inputparams, XN702014Req.class);
        StringValidater.validateBlank(xn702014Req.getUserId(),
            xn702014Req.getOldTradePwd(), xn702014Req.getNewTradePwd());

    }

}
