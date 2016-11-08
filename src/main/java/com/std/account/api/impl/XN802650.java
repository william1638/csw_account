package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.dto.req.XN802650Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;

/**
 * 意外查询：不平账查询。----调用者的权利
 * @author: myb858 
 * @since: 2016年11月5日 下午12:45:36 
 * @history:
 */
public class XN802650 extends AProcessor {
    // private ILedgerAO ledgerAO =
    // SpringContextHolder.getBean(ILedgerAO.class);

    private XN802650Req req = null;

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
