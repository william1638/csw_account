package com.std.account.api.impl;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802604Req;
import com.std.account.dto.res.XN802604Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 支付取现订单
 * @author: myb858 
 * @since: 2015年8月26日 下午4:10:50 
 * @history:
 */
public class XN802604 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN802604Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long payFree = StringValidater.toLong(req.getPayFee());
        cqOrderAO.doPayWithdraw(req.getWithdrawNo(), req.getPayUser(),
            req.getPayResult(), req.getPayNote(), req.getPayNo(), payFree,
            req.getWorkDate());
        return new XN802604Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802604Req.class);
        StringValidater.validateBlank(req.getWithdrawNo(), req.getPayUser(),
            req.getPayResult(), req.getPayNote(), req.getWorkDate());
    }

}
