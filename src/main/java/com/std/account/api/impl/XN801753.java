package com.std.account.api.impl;

import com.std.account.ao.IUserCompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801753Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 根据绑定人UserId获取企业列表
 * @author: myb858 
 * @since: 2016年1月14日 下午5:38:34 
 * @history:
 */
public class XN801753 extends AProcessor {
    private IUserCompanyAO userCompanyAO = SpringContextHolder
        .getBean(IUserCompanyAO.class);

    private XN801753Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return userCompanyAO.queryUserCompanyListByUserId(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801753Req.class);
        StringValidater.validateBlank(req.getUserId());

    }

}
