package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.ICQOrderAO;
import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.ZZOrder;
import com.std.account.dto.req.XN802705Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 查询转入转出列表
 * @author: myb858 
 * @since: 2015年10月27日 下午3:52:51 
 * @history:
 */
public class XN802705 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802705Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ZZOrder condition = new ZZOrder();

        condition.setZzNo(req.getZzNo());
        condition.setStatus(req.getStatus());
        condition.setDirection(req.getDirection());
        condition.setOppositeSystem(req.getOppositeSystem());
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
        return zzOrderAO.queryZZOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802705Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
