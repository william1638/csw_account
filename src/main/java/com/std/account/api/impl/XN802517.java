package com.std.account.api.impl;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802517Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 内部转账(指定用户编号和币种进行转账)(暂时不用)
 * @author: xieyj 
 * @since: 2016年12月25日 下午3:14:31 
 * @history:
 */
public class XN802517 extends AProcessor {

    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802517Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long transAmount = StringValidater.toLong(req.getTransAmount());
        accountAO.transAmountCZB(req.getSystemCode(), req.getFromUserId(),
            req.getToUserId(), req.getCurrency(), transAmount,
            req.getBizType(), req.getBizNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802517Req.class);
        StringValidater.validateBlank(req.getFromUserId(), req.getToUserId(),
            req.getCurrency(), req.getBizType(), req.getBizNote(),
            req.getSystemCode());
        StringValidater.validateAmount(req.getTransAmount());
    }
}
