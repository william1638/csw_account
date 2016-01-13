package com.std.account.api.impl;

import com.std.account.ao.IBankAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702458Req;
import com.std.account.dto.res.XN702458Res;
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

    private XN702458Req xn702458Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702458Res(bankAO.dropBank(StringValidater
            .toLong(xn702458Req.getId())));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702458Req = JsonUtil.json2Bean(inputparams, XN702458Req.class);
        StringValidater.validateBlank(xn702458Req.getId());

    }
}
