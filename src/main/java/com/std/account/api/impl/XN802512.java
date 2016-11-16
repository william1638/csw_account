package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.dto.req.XN802651Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;

/**
 * 内部账不平账调整
 * @author: myb858 
 * @since: 2016年11月16日 下午3:26:18 
 * @history:
 */
public class XN802512 extends AProcessor {

    // private ILedgerAO ledgerAO =
    // SpringContextHolder.getBean(ILedgerAO.class);

    private XN802651Req req = null;

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
