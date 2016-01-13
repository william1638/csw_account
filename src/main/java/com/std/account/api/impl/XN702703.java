package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.ICQOrderAO;
import com.std.account.ao.IXNBOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.XNBOrder;
import com.std.account.dto.req.XN702703Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分页查询虚拟币订单
 * @author: myb858 
 * @since: 2015年10月27日 下午3:51:58 
 * @history:
 */
public class XN702703 extends AProcessor {
    private IXNBOrderAO xnbOrderAO = SpringContextHolder
        .getBean(IXNBOrderAO.class);

    private XN702703Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        XNBOrder condition = new XNBOrder();

        condition.setXnbNo(req.getXnbNo());
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());
        condition.setApproveUser(req.getApproveUser());
        condition.setAccountNumber(req.getAccountNumber());
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
        return xnbOrderAO.queryXNBOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702703Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
