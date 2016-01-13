package com.std.account.api.impl;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Bank;
import com.std.account.dto.req.XN702455Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 银行-列表查询
 * @author: myb858 
 * @since: 2015年9月18日 下午2:12:16 
 * @history:
 */
public class XN802005 extends AProcessor {
    private IBankAO bankAO = SpringContextHolder.getBean(IBankAO.class);

    private XN702455Req xn702455Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bank condition = new Bank();
        condition.setId(StringValidater.toLong(xn702455Req.getId()));
        condition.setBankNo(xn702455Req.getBankNo());
        condition.setBankType(xn702455Req.getBankType());
        condition.setChannelNo(xn702455Req.getChannelNo());
        condition.setIsEnable(xn702455Req.getIsEnable());
        condition.setQuickType(xn702455Req.getQuickType());
        return bankAO.queryBankList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702455Req = JsonUtil.json2Bean(inputparams, XN702455Req.class);

    }

}
