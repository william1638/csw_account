package com.std.account.api.impl;

import com.std.account.ao.IBankCardAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801204Req;
import com.std.account.dto.res.XN801204Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 绑定银行卡（多卡）
 * @author: myb858 
 * @since: 2015年8月23日 下午2:36:52 
 * @history:
 */
public class XN801204 extends AProcessor {
    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN801204Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardAO.doBindBandCard(req.getUserId(), req.getType(),
            req.getBankCode(), req.getBankName(), req.getBankCardNo(),
            req.getSubbranch(), req.getBindMobile());
        return new XN801204Res(true);

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801204Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getType(),
            req.getBankCode(), req.getBankName(), req.getBankCardNo());
    }

}
