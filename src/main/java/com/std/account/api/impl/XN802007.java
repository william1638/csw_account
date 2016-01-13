package com.std.account.api.impl;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Bank;
import com.std.account.dto.req.XN702457Req;
import com.std.account.dto.res.XN702457Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 银行-增加
 * @author: myb858 
 * @since: 2015年9月18日 下午2:25:13 
 * @history:
 */
public class XN802007 extends AProcessor {
    private IBankAO bankAO = SpringContextHolder.getBean(IBankAO.class);

    private XN702457Req xn702457Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bank data = new Bank();
        data.setBankName(xn702457Req.getBankName());
        data.setBankNo(xn702457Req.getBankNo());
        data.setBankType(xn702457Req.getBankType());
        data.setChannelNo(xn702457Req.getChannelNo());
        data.setIsEnable(xn702457Req.getIsEnable());
        data.setQuickType(xn702457Req.getQuickType());
        return new XN702457Res(bankAO.addBank(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702457Req = JsonUtil.json2Bean(inputparams, XN702457Req.class);
        StringValidater.validateBlank(xn702457Req.getBankName(),
            xn702457Req.getBankNo(), xn702457Req.getBankType(),
            xn702457Req.getChannelNo(), xn702457Req.getIsEnable(),
            xn702457Req.getQuickType());

    }

}
