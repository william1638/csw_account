package com.std.account.api.impl;

import com.std.account.ao.IBankCardAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.BankCard;
import com.std.account.dto.req.XN702202Req;
import com.std.account.dto.res.XN702202Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 根据userId获取银行卡
 * @author: myb858 
 * @since: 2015年8月23日 下午2:45:57 
 * @history:
 */
public class XN702202 extends AProcessor {
    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN702202Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BankCard bankcard = bankCardAO.getBankCard(req.getUserId());
        XN702202Res res = new XN702202Res();
        if (bankcard != null) {
            res.setUserId(bankcard.getUserId());
            res.setBankCode(bankcard.getBankCode());
            res.setBankName(bankcard.getBankName());
            res.setSubbranch(bankcard.getSubbranch());
            res.setBindMobile(bankcard.getBindMobile());
            res.setBankCardNo(bankcard.getBankCardNo());
            res.setStatus(bankcard.getStatus());
            res.setCreateDatetime(bankcard.getCreateDatetime());
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702202Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
