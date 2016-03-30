package com.std.account.api.impl;

import com.std.account.ao.ICompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801300Req;
import com.std.account.dto.res.XN801300Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 企业基本信息添加
 * @author: myb858 
 * @since: 2016年1月14日 下午3:31:40 
 * @history:
 */
public class XN801300 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN801300Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String companyId = companyAO.addCompany(req.getCompanyName(),
            req.getLicenceNo(), req.getIdKind(), req.getIdNo(),
            req.getRealName(), req.getCurrency(),
            StringValidater.toLong(req.getCapital()), req.getProvince(),
            req.getCity(), req.getApplyUser(), req.getAddress());
        return new XN801300Res(companyId);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801300Req.class);
        StringValidater.validateBlank(req.getCompanyName(), req.getLicenceNo(),
            req.getIdKind(), req.getIdNo(), req.getRealName(),
            req.getCurrency(), req.getCapital(), req.getProvince(),
            req.getCity(), req.getApplyUser());
        StringValidater.validateNumber(req.getCapital());

    }
}
