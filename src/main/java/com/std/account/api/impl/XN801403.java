package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.UserLoginLog;
import com.std.account.dto.req.XN801403Req;
import com.std.account.dto.res.XN801403Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 根据userId获取最新登录日志
 * @author: myb858 
 * @since: 2015年9月29日 上午10:47:57 
 * @history:
 */
public class XN801403 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801403Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        UserLoginLog log = userAO.doGetLatestUserLoginLog(req.getUserId());
        XN801403Res res = new XN801403Res();
        if (log != null) {
            res.setIsSuccess(log.getIsSuccess());
            res.setLoginDatetime(log.getLoginDatetime());
            res.setLoginIp(log.getLoginIp());
            res.setUserId(log.getUserId());
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801403Req.class);
        StringValidater.validateBlank(req.getUserId());

    }

}
