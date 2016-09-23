package com.std.account.api.impl;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802314Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 用户和商户间的消费，返现
 * @author: xieyj 
 * @since: 2016年9月3日 上午10:02:09 
 * @history:
 */
public class XN802314 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802314Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        Long cnyAmount = StringValidater.toLong(req.getCnyAmount());
        Long jfCashBack = StringValidater.toLong(req.getJfCashBack());
        Long cnyCashBack = StringValidater.toLong(req.getCnyCashBack());
        zzOrderAO.doShopMerchant(req.getFromUserId(), req.getToUserId(),
            amount, cnyAmount, jfCashBack, cnyCashBack);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802314Req.class);
        StringValidater.validateBlank(req.getFromUserId(), req.getToUserId());
        StringValidater.validateAmount(req.getAmount(), req.getCnyAmount());
    }
}
