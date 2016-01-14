package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Bank;
import com.std.account.dto.req.XN802006Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 银行-分页查询
 * @author: myb858 
 * @since: 2015年9月18日 下午2:19:48 
 * @history:
 */
public class XN802006 extends AProcessor {
    private IBankAO bankAO = SpringContextHolder.getBean(IBankAO.class);

    private XN802006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bank condition = new Bank();
        condition.setId(StringValidater.toLong(req.getId()));
        condition.setBankNo(req.getBankNo());
        condition.setBankType(req.getBankType());
        condition.setChannelNo(req.getChannelNo());
        condition.setIsEnable(req.getIsEnable());
        condition.setQuickType(req.getQuickType());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBankAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return bankAO.queryBankPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802006Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
