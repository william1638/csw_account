package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Bank;
import com.std.account.dto.req.XN702456Req;
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

    private XN702456Req xn702456Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bank condition = new Bank();
        condition.setId(StringValidater.toLong(xn702456Req.getId()));
        condition.setBankNo(xn702456Req.getBankNo());
        condition.setBankType(xn702456Req.getBankType());
        condition.setChannelNo(xn702456Req.getChannelNo());
        condition.setIsEnable(xn702456Req.getIsEnable());
        condition.setQuickType(xn702456Req.getQuickType());

        String column = xn702456Req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBankAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, xn702456Req.getOrderDir());
        int start = Integer.valueOf(xn702456Req.getStart());
        int limit = Integer.valueOf(xn702456Req.getLimit());
        return bankAO.queryBankPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702456Req = JsonUtil.json2Bean(inputparams, XN702456Req.class);
        StringValidater.validateNumber(xn702456Req.getStart(),
            xn702456Req.getLimit());

    }

}
