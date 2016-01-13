package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702005Req;
import com.std.account.dto.res.XN702005Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 设置交易密码
 * @author: myb858 
 * @since: 2015年8月23日 下午2:19:53 
 * @history:
 */
public class XN702005 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702005Req xn702005Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702005Res(userAO.doSetTradePwd(xn702005Req.getUserId(),
            xn702005Req.getTradePwd(), xn702005Req.getSmsCaptcha()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702005Req = JsonUtil.json2Bean(inputparams, XN702005Req.class);
        StringValidater.validateBlank(xn702005Req.getUserId(),
            xn702005Req.getTradePwd(), xn702005Req.getSmsCaptcha());
    }

}
