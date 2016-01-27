package com.std.account.api.impl;

import com.std.account.ao.IBankCardAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801708Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 获取当前用户个人银行以及公司银行卡列表
 * @author: myb858 
 * @since: 2016年1月27日 下午4:42:53 
 * @history:
 */
public class XN801708 extends AProcessor {
    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN801708Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.queryAllBankCardList(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801708Req.class);
        StringValidater.validateBlank(req.getUserId());

    }

}
