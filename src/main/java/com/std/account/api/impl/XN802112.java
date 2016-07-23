package com.std.account.api.impl;

import com.std.account.ao.IChargeAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Charge;
import com.std.account.dto.req.XN802112Req;
import com.std.account.dto.res.XN802112Res;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EFromType;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 线下充值订单申请审核一键通过
 * @author: myb858 
 * @since: 2015年8月26日 下午3:44:00 
 * @history:
 */
public class XN802112 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802112Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Charge data = new Charge();
        data.setAccountNumber(req.getAccountNumber());
        data.setAmount(StringValidater.toLong(req.getAmount()));
        data.setFromType(req.getFromType());
        data.setFromCode(req.getFromCode());

        data.setPdf(req.getPdf());
        data.setApproveUser(req.getApproveUser());
        data.setApproveNote(req.getApproveNote());
        data.setRefNo(req.getRefNo());
        return new XN802112Res(chargeAO.doChargeOfflineWithoutApp(data,
            ECurrency.CNY));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802112Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getFromType(), req.getFromCode(), req.getPdf());
        StringValidater.validateAmount(req.getAmount());

        EFromType c = EFromType.getFromTypeMap().get(req.getFromType());
        if (c == null) {
            throw new BizException("li779001", "fromType不在程序所能支持序列");
        }
    }
}
