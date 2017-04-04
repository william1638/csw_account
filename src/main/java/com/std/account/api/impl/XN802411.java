package com.std.account.api.impl;

import com.std.account.ao.IExchangeCurrencyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802411Req;
import com.std.account.dto.res.BooleanRes;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 审核虚拟币转人民币
 * @author: myb858 
 * @since: 2017年4月3日 下午9:02:11 
 * @history:
 */
public class XN802411 extends AProcessor {

    private IExchangeCurrencyAO exchangeCurrencyAO = SpringContextHolder
        .getBean(IExchangeCurrencyAO.class);

    private XN802411Req req = null;

    /*
     * String rate = null; String ckey = null; if
     * (EBizType.AJ_HB2FR.getCode().equals(bizType)) { ckey =
     * SysConstants.HB2FR; } else if
     * (EBizType.AJ_HBYJ2FR.getCode().equals(bizType)) { ckey =
     * SysConstants.HBYJ2FR; } else if
     * (EBizType.HBYJ2GXJL.getCode().equals(bizType)) { ckey =
     * SysConstants.HBYJ2GXJL; }
     */
    // rate = sysConfigBO.getConfigValue(ckey);
    // if (StringUtils.isBlank(rate)) {
    // throw new BizException("xn000000", "兑换比例不存在，请检查钱包汇率规则参数");
    // }
    // if (rate.equals(EBoolean.NO.getCode())) {
    // throw new BizException("xn000000", "兑换比例为0，不能兑换");
    // }
    // accountBO.doExchangeAmount(systemCode, code, rate, approveResult,
    // approver, approveNote);

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public Object doBusiness() throws BizException {
        for (String code : req.getCodeList()) {
            exchangeCurrencyAO.approveExchange(code, req.getApproveResult(),
                req.getApprover(), req.getApproveNote());
        }
        return new BooleanRes(true);
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802411Req.class);
        StringValidater.validateBlank(req.getApproveResult(),
            req.getApprover(), req.getApproveNote());
        StringValidater.validateListEmpty(req.getCodeList());
    }

}
