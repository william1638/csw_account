package com.std.account.api.impl;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802312Req;
import com.std.account.dto.res.PKCodeRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 划账（oss）
 * @author: xieyj 
 * @since: 2016年9月3日 上午10:02:09 
 * @history:
 */
public class XN802312 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802312Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        Long fee = StringValidater.toLong(req.getFee());
        String code = zzOrderAO.doHZOss(req.getFromAccountNumber(),
            req.getAccountNumber(), req.getDirection(), amount, fee,
            req.getRemark());
        return new PKCodeRes(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802312Req.class);
        StringValidater.validateBlank(req.getFromAccountNumber(),
            req.getAccountNumber(), req.getDirection(), req.getAmount(),
            req.getRemark());
        StringValidater.validateAmount(req.getAmount());
    }
}
