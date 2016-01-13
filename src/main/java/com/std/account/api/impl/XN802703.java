package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.CQOrder;
import com.std.account.dto.req.XN702702Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分页查询充值取现列表
 * @author: myb858 
 * @since: 2015年10月27日 下午3:51:05 
 * @history:
 */
public class XN802703 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN702702Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CQOrder condition = new CQOrder();

        condition.setCqNo(req.getCqNo());
        condition.setStatus(req.getStatus());
        condition.setDirection(req.getDirection());
        condition.setChannel(req.getChannel());

        condition.setBankCode(req.getBankCode());
        condition.setApproveUser(req.getApproveUser());
        condition.setPayUser(req.getPayUser());
        condition.setCheckUser(req.getCheckUser());
        condition.setWorkDate(DateUtil.remove_(req.getWorkDate()));
        condition.setAccountNumber(req.getAccountNumber());
        condition.setMobile(req.getMobile());
        condition.setRealName(req.getRealName());
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
        req = JsonUtil.json2Bean(inputparams, XN702702Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
