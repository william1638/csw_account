package com.std.account.api.impl;

import com.std.account.ao.IJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802511Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 手动回调
 * @author: xieyj 
 * @since: 2016年12月23日 下午10:37:46 
 * @history:
 */
public class XN802511 extends AProcessor {
    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN802511Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public Object doBusiness() throws BizException {
        jourAO.doCallBackChange(req.getCode(), req.getRollbackResult(),
            req.getRollbackUser(), req.getRollbackNote(), req.getSystemCode());
        return new BooleanRes(true);
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802511Req.class);
        StringValidater.validateBlank(req.getCode(), req.getRollbackUser(),
            req.getRollbackNote(), req.getSystemCode());
    }
}
