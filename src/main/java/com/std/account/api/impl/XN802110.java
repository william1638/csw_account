package com.std.account.api.impl;

import com.std.account.ao.IChargeAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802110Req;
import com.std.account.dto.res.XN802110Res;
import com.std.account.enums.EFromType;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 线下充值
 * @author: myb858 
 * @since: 2015年8月23日 下午4:40:12 
 * @history:
 */
public class XN802110 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802110Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        return new XN802110Res(chargeAO.doChargeOffline(req.getAccountNumber(),
            amount, req.getFromType(), req.getFromCode()));

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802110Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getFromType(), req.getFromCode());
        StringValidater.validateAmount(req.getAmount());

        EFromType c = EFromType.getFromTypeMap().get(req.getFromType());
        if (c == null) {
            throw new BizException("li779001", "fromType不在程序所能支持序列");
        }
    }
}
