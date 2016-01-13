package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Account;
import com.std.account.dto.req.XN702700Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分页查询账户列表
 * @author: myb858 
 * @since: 2015年10月27日 下午2:34:06 
 * @history:
 */
public class XN702700 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN702700Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Account condition = new Account();
        condition.setAccountNumber(req.getAccountNumber());
        condition.setStatus(req.getStatus());
        condition.setUserId(req.getUserId());
        condition.setMobile(req.getMobile());
        condition.setRealName(req.getRealName());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAccountAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return accountAO.queryAccountPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702700Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
