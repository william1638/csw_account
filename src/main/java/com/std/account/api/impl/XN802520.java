package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.dto.req.XN802400Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;

/**
 * 最优渠道查找（智能路由）
 * @author: myb858 
 * @since: 2016年11月16日 下午1:28:09 
 * @history:
 */
public class XN802520 extends AProcessor {

    private XN802400Req req = null;

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
