package com.std.account.api.impl;

import com.std.account.ao.IBankCardAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801214Req;
import com.std.account.dto.res.XN801214Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-修改银行卡
 * @author: myb858 
 * @since: 2016年1月13日 下午2:10:42 
 * @history:
 */
public class XN801214 extends AProcessor {

    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN801214Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardAO.doRebindBankCard(StringValidater.toLong(req.getId()),
            req.getUserId(), req.getBankCode(), req.getBankName(),
            req.getBankCardNo(), req.getSubbranch(), req.getBindMobile());
        return new XN801214Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801214Req.class);
        StringValidater.validateBlank(req.getId(), req.getUserId(),
            req.getBankCode(), req.getBankName(), req.getBankCardNo());

    }

}
