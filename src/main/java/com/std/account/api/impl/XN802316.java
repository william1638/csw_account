package com.std.account.api.impl;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802316Req;
import com.std.account.dto.res.PKCodeRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 转账
 * @author: xieyj 
 * @since: 2016年9月23日 下午12:25:27 
 * @history:
 */
public class XN802316 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802316Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        Long fee = StringValidater.toLong(req.getFee());
        String code = zzOrderAO.doZZ(req.getFromUserId(), req.getToUserId(),
            req.getDirection(), amount, fee, req.getRemark());
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802316Req.class);
        StringValidater.validateBlank(req.getFromUserId(), req.getToUserId(),
            req.getDirection(), req.getAmount(), req.getRemark());
        StringValidater.validateAmount(req.getAmount());
    }
}
