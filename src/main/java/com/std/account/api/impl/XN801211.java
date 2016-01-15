package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801211Req;
import com.std.account.dto.res.XN801211Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 *辅助-找回交易密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:14:13 
 * @history:
 */
public class XN801211 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801211Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801211Res(userAO.doFindTradePwd(req.getUserId(),
            req.getTradePwd(), req.getSmsCaptcha(), req.getIdKind(),
            req.getIdNo()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801211Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getTradePwd(),
            req.getSmsCaptcha(), req.getIdKind(), req.getIdNo());

    }

}
