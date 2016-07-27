package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IWithdrawAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Withdraw;
import com.std.account.dto.req.XN802200Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 充值取现订单查询（oss）
 * @author: myb858 
 * @since: 2015年8月26日 下午3:13:34 
 * @history:
 */
public class XN802200 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802200Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Withdraw condition = new Withdraw();
        condition.setFromAccountNumber(req.getFromAccountNumber());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setCode(req.getCode());
        condition.setToType(req.getToType());
        condition.setToCode(req.getToCode());

        condition.setChannel(req.getChannel());
        condition.setRefNo(req.getRefNo());
        condition.setStatus(req.getStatus());
        condition.setApproveUser(req.getApproveUser());
        condition.setPayUser(req.getPayUser());

        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IWithdrawAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return withdrawAO.queryWithdrawPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802200Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
