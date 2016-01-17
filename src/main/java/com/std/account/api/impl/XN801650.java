package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801650Req;
import com.std.account.dto.res.XN801650Res;
import com.std.account.enums.EBoolean;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * KYC审核
 * @author: myb858 
 * @since: 2016年1月14日 下午3:41:29 
 * @history:
 */
public class XN801650 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801650Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doKYC(req.getCompanyId(), req.getKycUser(), req.getKycResult(),
            req.getKycNote(), req.getServeList(), req.getQuoteList(),
            req.getLevel());
        return new XN801650Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801650Req.class);
        StringValidater.validateBlank(req.getCompanyId(), req.getKycUser(),
            req.getKycResult(), req.getKycNote());
        if (EBoolean.YES.getCode().equalsIgnoreCase(req.getKycResult())) {
            StringValidater.validateBlank(req.getServeList(),
                req.getQuoteList(), req.getLevel());
        }
    }
}
