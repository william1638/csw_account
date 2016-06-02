package com.std.account.api.impl;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802000Req;
import com.std.account.dto.res.XN802000Res;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分配账户（方式一）
 * @author: myb858 
 * @since: 2016年5月25日 下午3:30:03 
 * @history:
 */
public class XN802000 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String currency = req.getCurrency().toUpperCase();
        ECurrency c = ECurrency.getCurrencyMap().get(currency);
        String accountNumber = accountAO.distributeAccount(req.getUserId(),
            req.getRealName(), c);
        return new XN802000Res(accountNumber);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802000Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getCurrency());
        String currency = req.getCurrency().toUpperCase();
        ECurrency c = ECurrency.getCurrencyMap().get(currency);
        if (c == null) {
            throw new BizException("li779001", "currency不在程序所能支持序列");
        }
    }

}
