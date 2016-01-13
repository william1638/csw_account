package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IAJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.AccountJour;
import com.std.account.dto.req.XN702601Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 查询资金明细
 * @author: myb858 
 * @since: 2015年8月23日 下午5:03:20 
 * @history:
 */
public class XN702601 extends AProcessor {
    private IAJourAO aJourAO = SpringContextHolder.getBean(IAJourAO.class);

    private XN702601Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AccountJour condition = new AccountJour();
        condition.setAjNo(StringValidater.toLong(req.getAjNo()));
        condition.setBizType(req.getBizType());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAJourAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return aJourAO.queryAccountJourPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702601Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getAccountNumber());

    }
}
