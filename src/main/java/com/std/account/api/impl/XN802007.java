package com.std.account.api.impl;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Bank;
import com.std.account.dto.req.XN802007Req;
import com.std.account.dto.res.XN802007Res;
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

    private XN802007Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bank data = new Bank();
        data.setBankName(req.getBankName());
        data.setBankNo(req.getBankNo());
        data.setBankType(req.getBankType());
        data.setChannelNo(req.getChannelNo());
        data.setIsEnable(req.getIsEnable());
        data.setQuickType(req.getQuickType());
        return new XN802007Res(bankAO.addBank(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802007Req.class);
        StringValidater.validateBlank(req.getBankName(), req.getBankNo(),
            req.getBankType(), req.getChannelNo(), req.getIsEnable(),
            req.getQuickType());

    }

}
