package com.std.account.api.impl;

import com.std.account.ao.IAJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802612Req;
import com.std.account.dto.res.XN802612Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 对账结果录入：告诉系统哪些交易流水已对账待调帐,此时红冲蓝补的订单已经生成
 * @author: myb858 
 * @since: 2016年1月15日 下午2:53:39 
 * @history:
 */
public class XN802612 extends AProcessor {
    private IAJourAO aJourAO = SpringContextHolder.getBean(IAJourAO.class);

    private XN802612Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        aJourAO.doCheckJour(StringValidater.toLong(req.getAjNo()),
            req.getCheckUser(), StringValidater.toLong(req.getAmount()));
        return new XN802612Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802612Req.class);
        StringValidater.validateBlank(req.getAjNo(), req.getCheckUser());
        StringValidater.validateAmount(req.getAmount());
    }
}
