package com.std.account.api.impl;

import com.std.account.ao.IOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702804Req;
import com.std.account.dto.res.XN702804Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 支付订单
 * @author: myb858 
 * @since: 2015年8月26日 下午4:10:50 
 * @history:
 */
public class XN702804 extends AProcessor {
    private IOrderAO orderAO = SpringContextHolder.getBean(IOrderAO.class);

    private XN702804Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long payFree = StringValidater.toLong(req.getPayFee());
        orderAO.doPay(req.getOrderType(), req.getOrderNo(), req.getPayUser(),
            req.getPayResult(), req.getRemark(), req.getPayNo(), payFree,
            req.getWorkDate());
        return new XN702804Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702804Req.class);
        StringValidater.validateBlank(req.getOrderType(), req.getOrderNo(),
            req.getPayUser(), req.getPayResult(), req.getRemark(),
            req.getWorkDate());
    }

}
