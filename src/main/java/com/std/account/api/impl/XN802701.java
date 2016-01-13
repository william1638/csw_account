package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IAJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.AccountJour;
import com.std.account.dto.req.XN702706Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分页查询资金明细
 * @author: myb858 
 * @since: 2015年10月29日 下午1:56:27 
 * @history:
 */
public class XN802701 extends AProcessor {
    private IAJourAO aJourAO = SpringContextHolder.getBean(IAJourAO.class);

    private XN702706Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AccountJour condition = new AccountJour();
        condition.setAjNo(StringValidater.toLong(req.getAjNo()));
        condition.setStatus(req.getStatus());
        condition.setBizType(req.getBizType());
        condition.setRefNo(req.getRefNo());
        condition.setWorkDate(DateUtil.remove_(req.getWorkDate()));
        condition.setCheckUser(req.getCheckUser());
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
        req = JsonUtil.json2Bean(inputparams, XN702706Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
