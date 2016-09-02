package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IChargeAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Charge;
import com.std.account.dto.req.XN802101Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分页查询充值列表(front)
 * @author: myb858 
 * @since: 2016年5月25日 下午4:35:11 
 * @history:
 */
public class XN802101 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802101Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Charge condition = new Charge();
        condition.setFromAccountNumber(req.getFromAccountNumber());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setCurrency(req.getCurrency());
        condition.setCode(req.getCode());
        condition.setFromType(req.getFromType());
        condition.setFromCode(req.getFromCode());

        condition.setChannel(req.getChannel());
        condition.setRefNo(req.getRefNo());
        condition.setStatus(req.getStatus());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IChargeAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return chargeAO.queryChargePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802101Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getAccountNumber());

    }
}
