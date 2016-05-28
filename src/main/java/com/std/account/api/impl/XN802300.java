package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.ZZOrder;
import com.std.account.dto.req.XN802300Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 查询转入转出列表(oss)
 * @author: myb858 
 * @since: 2015年10月27日 下午3:52:51 
 * @history:
 */
public class XN802300 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802300Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ZZOrder condition = new ZZOrder();
        condition.setCode(req.getCode());
        condition.setDirection(req.getDirection());
        condition.setAccountNumber(req.getAccountNumber());

        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IZZOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return zzOrderAO.queryZZOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802300Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
