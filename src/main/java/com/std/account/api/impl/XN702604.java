package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IHLOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.HLOrder;
import com.std.account.dto.req.XN702604Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 查询红冲蓝补订单
 * @author: myb858 
 * @since: 2015年8月26日 下午3:40:39 
 * @history:
 */
public class XN702604 extends AProcessor {
    private IHLOrderAO hlOrderAO = SpringContextHolder
        .getBean(IHLOrderAO.class);

    private XN702604Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        HLOrder condition = new HLOrder();
        condition.setAccountNumber(req.getAccountNumber());
        condition.setHlNo(req.getHlNo());
        condition.setStatus(req.getStatus());
        condition.setDirection(req.getDirection());

        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IHLOrderAO.DEFAULT_ORDER_COLUMN;
        }
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        condition.setOrder(column, req.getOrderDir());
        return hlOrderAO.queryHLOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702604Req.class);
        StringValidater.validateBlank(req.getAccountNumber());
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }
}
