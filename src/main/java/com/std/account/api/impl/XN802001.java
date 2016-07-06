package com.std.account.api.impl;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802001Req;
import com.std.account.dto.res.XN802001Res;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分配账户（方式二）
 * @author: xieyj 
 * @since: 2016年7月5日 下午3:13:46 
 * @history:
 */
public class XN802001 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String currency = req.getCurrency().toUpperCase();
        ECurrency c = ECurrency.getCurrencyMap().get(currency);
        String accountNumber = accountAO.distributeAccountTwo(req.getUserId(),
            req.getRealName(), c);
        return new XN802001Res(accountNumber);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802001Req.class);
        StringValidater.validateAmount(req.getAmount());
        StringValidater.validateBlank(req.getUserId(), req.getCurrency());
        String currency = req.getCurrency().toUpperCase();
        ECurrency c = ECurrency.getCurrencyMap().get(currency);
        if (c == null) {
            throw new BizException("li779001", "currency不在程序所能支持序列");
        }
    }

}
