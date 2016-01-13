package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702011Req;
import com.std.account.dto.res.XN702011Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-找回登录密码
 * @author: myb858 
 * @since: 2015年9月15日 下午2:11:01 
 * @history:
 */
public class XN702011 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702011Req xn702011Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702011Res(userAO.doFindLoginPwd(xn702011Req.getMobile(),
            xn702011Req.getNewLoginPwd(), xn702011Req.getSmsCaptcha()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702011Req = JsonUtil.json2Bean(inputparams, XN702011Req.class);
        StringValidater.validateBlank(xn702011Req.getMobile(),
            xn702011Req.getSmsCaptcha(), xn702011Req.getNewLoginPwd());

    }

}
