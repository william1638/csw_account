package com.std.account.api.impl;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Bank;
import com.std.account.dto.req.XN702459Req;
import com.std.account.dto.res.XN702459Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 银行-修改
 * @author: myb858 
 * @since: 2015年9月18日 下午2:25:39 
 * @history:
 */
public class XN802009 extends AProcessor {
    private IBankAO bankAO = SpringContextHolder.getBean(IBankAO.class);

    private XN702459Req xn702459Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bank data = new Bank();
        data.setId(StringValidater.toLong(xn702459Req.getId()));
        data.setBankName(xn702459Req.getBankName());
        data.setBankNo(xn702459Req.getBankNo());
        data.setBankType(xn702459Req.getBankType());
        data.setChannelNo(xn702459Req.getChannelNo());
        data.setIsEnable(xn702459Req.getIsEnable());
        data.setQuickType(xn702459Req.getQuickType());
        return new XN702459Res(bankAO.editBank(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702459Req = JsonUtil.json2Bean(inputparams, XN702459Req.class);
        StringValidater.validateBlank(xn702459Req.getId(),
            xn702459Req.getBankName(), xn702459Req.getBankNo(),
            xn702459Req.getBankType(), xn702459Req.getChannelNo(),
            xn702459Req.getIsEnable(), xn702459Req.getQuickType());
    }

}
