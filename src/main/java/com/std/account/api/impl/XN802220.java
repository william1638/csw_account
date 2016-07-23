package com.std.account.api.impl;

import com.std.account.ao.IWithdrawAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Withdraw;
import com.std.account.dto.req.XN802220Req;
import com.std.account.dto.res.XN802210Res;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 积分兑换(front/oss)
 * @author: xieyj 
 * @since: 2016年7月23日 上午9:37:48 
 * @history:
 */
public class XN802220 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802220Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Withdraw data = new Withdraw();
        data.setFromUserId(req.getFromUserId());
        data.setToUserId(req.getToUserId());
        data.setAmount(StringValidater.toLong(req.getAmount()));
        data.setPrice(StringValidater.toLong(req.getPrice()));

        data.setType(req.getType());
        data.setApplyUser(req.getApplyUser());
        return new XN802210Res(withdrawAO.doWithdrawOSS(data, ECurrency.XNB));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802220Req.class);
        StringValidater.validateBlank(req.getFromUserId(), req.getToUserId(),
            req.getType());
        StringValidater.validateAmount(req.getAmount(), req.getPrice());
    }

}
