package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702012Req;
import com.std.account.dto.res.XN702012Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-重置登录密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:13:26 
 * @history:
 */
public class XN801209 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702012Req xn702012Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702012Res(userAO.doResetLoginPwd(xn702012Req.getUserId(),
            xn702012Req.getOldLoginPwd(), xn702012Req.getNewLoginPwd()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702012Req = JsonUtil.json2Bean(inputparams, XN702012Req.class);
        StringValidater.validateBlank(xn702012Req.getUserId(),
            xn702012Req.getOldLoginPwd(), xn702012Req.getNewLoginPwd());

    }

}
