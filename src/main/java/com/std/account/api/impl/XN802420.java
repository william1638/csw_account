package com.std.account.api.impl;

import com.std.account.ao.IExchangeCurrencyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802420Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 人民币买虚拟币(菜狗：前端用户问平台购买)
 * @author: myb858 
 * @since: 2017年4月4日 上午11:52:14 
 * @history:
 */
public class XN802420 extends AProcessor {
    private IExchangeCurrencyAO exchangeCurrencyAO = SpringContextHolder
        .getBean(IExchangeCurrencyAO.class);

    private XN802420Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public Object doBusiness() throws BizException {
        return exchangeCurrencyAO.payExchange(req.getUserId(),
            StringValidater.toLong(req.getAmount()), req.getCurrency(),
            req.getPayType());
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802420Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getAmount(),
            req.getCurrency(), req.getPayType());
    }

}
