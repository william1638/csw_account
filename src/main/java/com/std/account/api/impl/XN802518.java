package com.std.account.api.impl;

import com.std.account.ao.IJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802518Req;
import com.std.account.dto.res.PKCodeRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 不同币种间内部兑换申请
 * @author: xieyj 
 * @since: 2016年12月25日 下午3:14:31 
 * @history:
 */
public class XN802518 extends AProcessor {

    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN802518Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        Long transAmount = StringValidater.toLong(req.getTransAmount());
        String code = jourAO.applyExchangeAmount(req.getSystemCode(),
            req.getUserId(), transAmount, req.getBizType());
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802518Req.class);
        StringValidater.validateBlank(req.getSystemCode(), req.getUserId(),
            req.getBizType(), req.getSystemCode());
        StringValidater.validateAmount(req.getTransAmount());
    }
}
