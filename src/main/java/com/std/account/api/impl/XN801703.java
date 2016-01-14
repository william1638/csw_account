package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IBankCardAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.BankCard;
import com.std.account.dto.req.XN801703Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分页获取绑定银行卡信息
 * @author: myb858 
 * @since: 2015年10月29日 下午2:49:19 
 * @history:
 */
public class XN801703 extends AProcessor {
    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN801703Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BankCard condition = new BankCard();
        condition.setId(StringValidater.toLong(req.getId()));
        condition.setUserId(req.getUserId());
        condition.setType(req.getType());
        condition.setBankCode(req.getBankCode());
        condition.setBankCardNo(req.getBankCardNo());
        condition.setStatus(req.getStatus());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBankCardAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return bankCardAO.queryBankCardPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801703Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
