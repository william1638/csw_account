package com.std.account.api.impl;

import com.std.account.ao.IJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802527Req;
import com.std.account.dto.res.XN802527Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 统计某种业务类型下的金额
 * @author: xieyj 
 * @since: 2017年2月23日 下午5:03:24 
 * @history:
 */
public class XN802527 extends AProcessor {

    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN802527Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = jourAO.getStatisticsTransAmount(req.getSystemCode(),
            req.getUserId(), req.getCurrency(), req.getBizType());
        return new XN802527Res(amount);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802527Req.class);
        StringValidater.validateBlank(req.getSystemCode(), req.getBizType());
    }
}
