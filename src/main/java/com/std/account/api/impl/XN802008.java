package com.std.account.api.impl;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802008Req;
import com.std.account.dto.res.XN802008Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 银行-删除
 * @author: myb858 
 * @since: 2015年9月18日 下午2:25:29 
 * @history:
 */
public class XN802008 extends AProcessor {
    private IBankAO bankAO = SpringContextHolder.getBean(IBankAO.class);

    private XN802008Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN802008Res(bankAO.dropBank(StringValidater.toLong(req
            .getId())));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802008Req.class);
        StringValidater.validateBlank(req.getId());

    }
}
