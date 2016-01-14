package com.std.account.api.impl;

import com.std.account.ao.IUserExtAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801205Req;
import com.std.account.dto.res.XN801205Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 设置头像
 * @author: myb858 
 * @since: 2015年9月15日 下午2:37:54 
 * @history:
 */
public class XN801205 extends AProcessor {
    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN801205Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801205Res(userExtAO.doRichPhoto(req.getUserId(),
            req.getPhoto()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801205Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getPhoto());

    }

}
