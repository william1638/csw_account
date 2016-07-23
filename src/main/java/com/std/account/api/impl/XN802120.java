package com.std.account.api.impl;

import com.std.account.ao.IChargeAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Charge;
import com.std.account.dto.req.XN802120Req;
import com.std.account.dto.res.XN802110Res;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 积分申请
 * @author: xieyj 
 * @since: 2016年7月23日 上午7:41:44 
 * @history:
 */
public class XN802120 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802120Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Charge data = new Charge();
        data.setFromUserId(req.getFromUserId());
        data.setToUserId(req.getToUserId());
        data.setAmount(StringValidater.toLong(req.getAmount()));
        data.setPrice(StringValidater.toLong(req.getPrice()));

        data.setType(req.getType());
        data.setPdf(req.getPdf());
        data.setApplyUser(req.getApplyUser());
        return new XN802110Res(chargeAO.doChargeOffline(data, ECurrency.XNB));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802120Req.class);
        StringValidater.validateBlank(req.getFromUserId(), req.getToUserId(),
            req.getType(), req.getPdf(), req.getApplyUser());
        StringValidater.validateAmount(req.getAmount(), req.getPrice());
    }
}
