package com.std.account.api.impl;

import java.util.ArrayList;
import java.util.List;

import com.std.account.ao.IJourAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802526Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.enums.EBizType;
import com.std.account.enums.EChannelType;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 前端提现申请,需输入交易密码
 * @author: xieyj 
 * @since: 2017年1月17日 下午3:40:37 
 * @history:
 */
public class XN802526 extends AProcessor {

    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN802526Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        Long transAmount = StringValidater.toLong(req.getTransAmount());
        List<String> channelTypeList = new ArrayList<String>();
        channelTypeList.add(EChannelType.CZB.getCode());
        jourAO.doCzQxAmount(req.getAccountNumber(), req.getBankcardNumber(),
            transAmount, EBizType.AJ_QX.getCode(), EBizType.AJ_QX.getValue(),
            channelTypeList, req.getSystemCode(), req.getTradePwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802526Req.class);
        StringValidater.validateBlank(req.getSystemCode(),
            req.getBankcardNumber(), req.getAccountNumber(), req.getTradePwd());
        StringValidater.validateAmount(req.getTransAmount());
    }
}
