package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801207Req;
import com.std.account.dto.res.XN801207Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-修改手机号
 * @author: myb858 
 * @since: 2015年9月15日 下午2:36:27 
 * @history:
 */
public class XN801207 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801207Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801207Res(userAO.doChangeMoblie(req.getUserId(),
            req.getNewMobile(), req.getSmsCaptcha(), req.getTradePwd()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801207Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha(), req.getTradePwd());
    }

}
