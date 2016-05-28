package com.std.account.api.impl;

import com.std.account.ao.IWithdrawAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802211Req;
import com.std.account.dto.res.XN802211Res;
import com.std.account.enums.EToType;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 线下取现(front)
 * @author: myb858 
 * @since: 2015年8月23日 下午4:43:48 
 * @history:
 */
public class XN802211 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802211Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        return new XN802211Res(withdrawAO.doWithdrawOffline(
            req.getAccountNumber(), amount, req.getToType(), req.getToCode(),
            req.getTradePwd()));

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802211Req.class);
        StringValidater.validateBlank(req.getAccountNumber(), req.getAmount(),
            req.getToType(), req.getToCode(), req.getTradePwd());
        StringValidater.validateAmount(req.getAmount());

        EToType c = EToType.getToTypeMap().get(req.getToType());
        if (c == null) {
            throw new BizException("li779001", "toType不在程序所能支持序列");
        }
    }
}
