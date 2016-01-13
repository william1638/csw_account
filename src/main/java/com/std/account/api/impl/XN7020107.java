package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.cms.ao.INavigationAO;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020107Req;
import com.std.account.dto.res.XN7020107Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/** 
 * 更改导航状态
 * @author: xieyj 
 * @since: 2016年1月5日 下午5:34:22 
 * @history:
 */
public class XN7020107 extends AProcessor {
    private INavigationAO navigationAO = SpringContextHolder
        .getBean(INavigationAO.class);

    private XN7020107Req xn7020107Req = null;

    @Override
    public Object doBusiness() throws BizException {
        boolean result = navigationAO.changeNavigationStatus(
            xn7020107Req.getCode(), xn7020107Req.getStatus(),
            xn7020107Req.getUpdater());
        return new XN7020107Res(result);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020107Req = JsonUtil.json2Bean(inputparams, XN7020107Req.class);
        StringValidater.validateBlank(xn7020107Req.getCode(),
            xn7020107Req.getStatus(), xn7020107Req.getUpdater());
    }
}
