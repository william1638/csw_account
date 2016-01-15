package com.std.account.api.impl;

import com.std.account.ao.ICompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801752Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 查询企业详情
 * @author: myb858 
 * @since: 2016年1月14日 下午3:44:15 
 * @history:
 */
public class XN801752 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN801752Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return companyAO.doGetCompany(req.getCompanyId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801752Req.class);
        StringValidater.validateBlank(req.getCompanyId());

    }

}
