package com.std.account.api.impl;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802313Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 两个用户之间划账（oss）
 * @author: xieyj 
 * @since: 2016年9月3日 上午10:02:09 
 * @history:
 */
public class XN802313 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802313Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        Long cnyAmount = StringValidater.toLong(req.getCnyAmount());
        Long fee = StringValidater.toLong(req.getFee());
        zzOrderAO.doBuyTransfer(req.getFromUserId(), req.getToUserId(),
            req.getDirection(), amount, cnyAmount, fee, req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802313Req.class);
        StringValidater.validateBlank(req.getFromUserId(), req.getToUserId(),
            req.getDirection(), req.getRemark());
        StringValidater.validateAmount(req.getAmount(), req.getCnyAmount());
    }
}
