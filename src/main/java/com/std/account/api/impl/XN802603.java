package com.std.account.api.impl;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802601Req;
import com.std.account.dto.req.XN802603Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 
 * 审批线下取现订单
 * @author: myb858 
 * @since: 2016年1月13日 下午8:18:05 
 * @history:
 */
public class XN802603 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN802603Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802601Req.class);
        StringValidater.validateBlank(req.getOrderNo(), req.getApproveUser(),
            req.getApproveResult(), req.getRemark());

    }

}
