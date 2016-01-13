package com.std.account.api.impl;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702515Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 易宝一键支付
 * @author: myb858 
 * @since: 2015年11月16日 下午5:11:41 
 * @history:
 */
public class XN702515 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN702515Req xn702515Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(xn702515Req.getAmount());
        Long fee = StringValidater.toLong(xn702515Req.getFee());
        return cqOrderAO.doInstantPay(xn702515Req.getAccountNumber(), amount,
            fee, xn702515Req.getUserIp(), xn702515Req.getIdNo(),
            xn702515Req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702515Req = JsonUtil.json2Bean(inputparams, XN702515Req.class);
        StringValidater.validateBlank(xn702515Req.getAmount(),
            xn702515Req.getIdNo(), xn702515Req.getUserId());

    }
}
