package com.std.account.api.impl;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802311Req;
import com.std.account.dto.res.XN802311Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 转账（front）
 * @author: myb858 
 * @since: 2016年5月26日 下午3:49:43 
 * @history:
 */
public class XN802311 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802311Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        Long fee = StringValidater.toLong(req.getFee());
        String code = zzOrderAO
            .doTransferFRONT(req.getAccountNumber(), req.getDirection(),
                amount, fee, req.getRemark(), req.getTradePwd());
        return new XN802311Res(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802311Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getDirection(), req.getAmount(), req.getFee(), req.getRemark(),
            req.getTradePwd());
        StringValidater.validateAmount(req.getAmount(), req.getFee());

    }

}
