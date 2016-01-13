package com.std.account.api.impl;

import com.std.account.ao.IBankCardAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702006Req;
import com.std.account.dto.res.XN702006Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 绑定/修改银行卡（一人一卡）
 * @author: myb858 
 * @since: 2015年8月23日 下午2:36:52 
 * @history:
 */
public class XN702006 extends AProcessor {
    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN702006Req xn702006Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702006Res(bankCardAO.doBindBandCard(
            xn702006Req.getUserId(), xn702006Req.getBankCode(),
            xn702006Req.getBankName(), xn702006Req.getBankCardNo(),
            xn702006Req.getSubbranch(), xn702006Req.getBindMobile()));

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702006Req = JsonUtil.json2Bean(inputparams, XN702006Req.class);
        StringValidater.validateBlank(xn702006Req.getUserId(),
            xn702006Req.getBankCode(), xn702006Req.getBankName(),
            xn702006Req.getBankCardNo());
    }

}
