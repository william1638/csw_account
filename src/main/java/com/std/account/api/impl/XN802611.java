package com.std.account.api.impl;

import com.std.account.ao.IHLOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802611Req;
import com.std.account.dto.res.XN802611Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 人工干预账户审批：还是需要对账的
 * @author: myb858 
 * @since: 2016年1月13日 下午8:18:50 
 * @history:
 */
public class XN802611 extends AProcessor {
    private IHLOrderAO hlOrderAO = SpringContextHolder
        .getBean(IHLOrderAO.class);

    private XN802611Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        hlOrderAO.doApprove(req.getHlNo(), req.getApproveUser(),
            req.getApproveResult(), req.getApproveNote());
        return new XN802611Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802611Req.class);
        StringValidater.validateBlank(req.getHlNo(), req.getApproveUser(),
            req.getApproveResult(), req.getApproveNote());

    }

}
