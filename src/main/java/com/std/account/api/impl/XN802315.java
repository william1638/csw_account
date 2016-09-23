package com.std.account.api.impl;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802315Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 用户用现金向商家购买积分
 * @author: xieyj 
 * @since: 2016年9月23日 下午12:25:27 
 * @history:
 */
public class XN802315 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802315Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        Long cnyAmount = StringValidater.toLong(req.getCnyAmount());
        zzOrderAO.doBuyJf(req.getUserId(), cnyAmount, amount);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802315Req.class);
        StringValidater.validateBlank(req.getUserId());
        StringValidater.validateAmount(req.getCnyAmount(), req.getAmount());
    }
}
