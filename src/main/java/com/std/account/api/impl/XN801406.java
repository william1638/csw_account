package com.std.account.api.impl;

import com.std.account.ao.IBankCardAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801406Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 银行卡-获取详情
 * @author: myb858 
 * @since: 2015年8月23日 下午2:45:57 
 * @history:
 */
public class XN801406 extends AProcessor {
    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN801406Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.getBankCard(StringValidater.toLong(req.getId()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801406Req.class);
        StringValidater.validateBlank(req.getId());
    }
}
