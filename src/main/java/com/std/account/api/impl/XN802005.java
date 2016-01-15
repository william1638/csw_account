package com.std.account.api.impl;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Bank;
import com.std.account.dto.req.XN802005Req;
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

    private XN802005Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bank condition = new Bank();
        condition.setId(StringValidater.toLong(req.getId()));
        condition.setBankNo(req.getBankNo());
        condition.setBankType(req.getBankType());
        condition.setChannelNo(req.getChannelNo());
        condition.setIsEnable(req.getIsEnable());
        condition.setQuickType(req.getQuickType());
        return bankAO.queryBankList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802005Req.class);

    }

}
