package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IAFJourAO;
import com.std.account.ao.IAJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.AccountFrozenJour;
import com.std.account.dto.req.XN702602Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 查询冻结明细
 * @author: myb858 
 * @since: 2015年8月26日 上午10:56:36 
 * @history:
 */
public class XN802402 extends AProcessor {
    private IAFJourAO aFJourAO = SpringContextHolder.getBean(IAFJourAO.class);

    private XN702602Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        AccountFrozenJour condition = new AccountFrozenJour();
        condition.setAfjNo(StringValidater.toLong(req.getAfjNo()));
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
        return aFJourAO.queryAccountFrozenJourPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702602Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getAccountNumber());
    }

}
