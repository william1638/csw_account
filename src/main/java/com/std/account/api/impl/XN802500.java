package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.dto.req.XN802500Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;

/**
 * 最优渠道查询----调用者的权利
 * @author: myb858 
 * @since: 2016年11月5日 下午12:44:28 
 * @history:
 */
public class XN802500 extends AProcessor {

    private XN802500Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        // req = JsonUtil.json2Bean(inputparams, XN802500Req.class);
        // StringValidater.validateBlank(req.getCompanyCode(),
        // req.getChannelType());

    }

}
