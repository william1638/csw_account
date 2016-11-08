package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.dto.req.XN802600Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;

/**
 * 本系统账户间资金划转：内部账划转。
 * @author: myb858 
 * @since: 2016年11月5日 下午12:45:09 
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
