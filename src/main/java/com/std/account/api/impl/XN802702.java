package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IAFJourAO;
import com.std.account.ao.IAJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.AccountFrozenJour;
import com.std.account.dto.req.XN802702Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分页查询冻结资金流水
 * @author: myb858 
 * @since: 2015年10月29日 下午1:57:45 
 * @history:
 */
public class XN802702 extends AProcessor {
    private IAFJourAO aFJourAO = SpringContextHolder.getBean(IAFJourAO.class);

    private XN802702Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AccountFrozenJour condition = new AccountFrozenJour();
        condition.setAfjNo(StringValidater.toLong(req.getAfjNo()));
        condition.setBizType(req.getBizType());
        condition.setRefNo(req.getRefNo());
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
        req = JsonUtil.json2Bean(inputparams, XN802702Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
