package com.std.account.api.impl;

import com.std.account.ao.IChargeAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Charge;
import com.std.account.dto.req.XN802122Req;
import com.std.account.dto.res.PKCodeRes;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 线下积分申请审核一键通过
 * @author: xieyj 
 * @since: 2016年7月23日 上午6:34:41 
 * @history:
 */
public class XN802122 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802122Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Charge data = new Charge();
        data.setFromUserId(req.getFromUserId());
        data.setToUserId(req.getToUserId());
        data.setAmount(StringValidater.toLong(req.getAmount()));
        data.setPrice(StringValidater.toLong(req.getPrice()));

        data.setType(req.getType());
        data.setPdf(req.getPdf());
        data.setApproveUser(req.getApproveUser());
        data.setApproveNote(req.getApproveNote());
        data.setRefNo(req.getRefNo());
        return new PKCodeRes(chargeAO.doChargeOfflineWithoutApp(data,
            ECurrency.XNB));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802122Req.class);
        StringValidater.validateBlank(req.getFromUserId(), req.getToUserId(),
            req.getType(), req.getPdf(), req.getApproveUser(),
            req.getApproveNote(), req.getRefNo());
        StringValidater.validateAmount(req.getAmount(), req.getPrice());
    }
}
