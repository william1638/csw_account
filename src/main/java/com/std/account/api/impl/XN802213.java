package com.std.account.api.impl;

import com.std.account.ao.IWithdrawAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802213Req;
import com.std.account.dto.res.XN802213Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 支付取现订单
 * @author: myb858 
 * @since: 2015年8月26日 下午4:10:50 
 * @history:
 */
public class XN802213 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802213Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long payFree = StringValidater.toLong(req.getFee());
        withdrawAO.doPayWithdraw(req.getWithdrawNo(), req.getPayUser(),
            req.getPayResult(), req.getPayNote(), req.getRefNo(), payFree);
        return new XN802213Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802213Req.class);
        StringValidater.validateBlank(req.getWithdrawNo(), req.getPayUser(),
            req.getPayResult(), req.getPayNote());
    }

}
