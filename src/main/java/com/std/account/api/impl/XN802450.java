package com.std.account.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802450Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 一人创建多账户
 * @author: xieyj 
 * @since: 2016年12月23日 下午7:47:21 
 * @history:
 */
public class XN802450 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802450Req req = null;

    /** 
     * @see com.xnjr.base.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        accountAO.distributeAccount(req.getUserId(), req.getRealName(),
            req.getType(), req.getCurrencyList(), req.getSystemCode());
        return new BooleanRes(true);
    }

    /** 
     * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802450Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getType(),
            req.getSystemCode());
        if (CollectionUtils.isEmpty(req.getCurrencyList())) {
            new BizException("XN0000", "账户币种不能为空");
        }
    }

}
