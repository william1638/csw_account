package com.std.account.api.impl;

import com.std.account.ao.IBankCardAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801204Req;
import com.std.account.dto.req.XN801213Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-删除银行卡
 * @author: myb858 
 * @since: 2016年1月13日 下午2:10:08 
 * @history:
 */
public class XN801213 extends AProcessor {
    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN801213Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801204Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getBankCode(),
            req.getBankName(), req.getBankCardNo());

    }

}
