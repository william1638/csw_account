package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.CQOrder;
import com.std.account.dto.req.XN702603Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 充值取现订单查询
 * @author: myb858 
 * @since: 2015年8月26日 下午3:13:34 
 * @history:
 */
public class XN702603 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN702603Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        CQOrder condition = new CQOrder();
        condition.setAccountNumber(req.getAccountNumber());
        condition.setCqNo(req.getCqNo());
        condition.setStatus(req.getStatus());
        condition.setDirection(req.getDirection());
        condition.setChannel(req.getChannel());

        condition.setBankCode(req.getBankCode());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICQOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return cqOrderAO.queryCQOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702603Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getAccountNumber());
    }

}
