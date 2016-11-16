package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.dto.req.XN802600Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;

/**
 * 校对账户余额：账本的余额理论上应该保证绝对正确性，供客户放篡改用。----调用者的权利
 * @author: myb858 
 * @since: 2016年11月5日 下午12:45:24 
 * @history:
 */
public class XN802600 extends AProcessor {
    // private ILedgerAO ledgerAO =
    // SpringContextHolder.getBean(ILedgerAO.class);

    private XN802600Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        // TODO Auto-generated method stub

    }

}
