package com.std.account.api.impl;

import com.std.account.ao.IWithdrawAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802210Req;
import com.std.account.dto.res.XN802210Res;
import com.std.account.enums.EToType;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 代线下取现(oss)
 * @author: myb858 
 * @since: 2016年1月13日 下午8:27:20 
 * @history:
 */
public class XN802210 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802210Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        return new XN802210Res(withdrawAO.doWithdrawOSS(req.getAccountNumber(),
            amount, req.getToType(), req.getToCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802210Req.class);
        StringValidater.validateBlank(req.getAccountNumber(), req.getAmount(),
            req.getToType(), req.getToCode());
        StringValidater.validateAmount(req.getAmount());

        EToType c = EToType.getToTypeMap().get(req.getToType());
        if (c == null) {
            throw new BizException("li779001", "toType不在程序所能支持序列");
        }

    }

}
