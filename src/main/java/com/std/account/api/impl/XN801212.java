package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801212Req;
import com.std.account.dto.res.XN801212Res;
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

    private XN801212Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801212Res(userAO.doResetTradePwd(req.getUserId(),
            req.getOldTradePwd(), req.getNewTradePwd()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801212Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getOldTradePwd(),
            req.getNewTradePwd());

    }

}
