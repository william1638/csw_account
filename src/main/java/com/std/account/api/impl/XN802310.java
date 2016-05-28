package com.std.account.api.impl;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802310Req;
import com.std.account.dto.res.XN802310Res;
import com.std.account.enums.EDirection;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 转账（OSS）
 * @author: myb858 
 * @since: 2016年5月26日 下午3:49:18 
 * @history:
 */
public class XN802310 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN802310Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        Long fee = StringValidater.toLong(req.getFee());
        String code = zzOrderAO.doTransferOSS(req.getAccountNumber(),
            req.getDirection(), amount, fee, req.getRemark());
        return new XN802310Res(code);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802310Req.class);
        StringValidater.validateBlank(req.getAccountNumber(),
            req.getDirection(), req.getAmount(), req.getFee(), req.getRemark());
        StringValidater.validateAmount(req.getAmount(), req.getFee());

        EDirection c = EDirection.getDirectionMap().get(req.getDirection());
        if (c == null) {
            throw new BizException("li779001", "direction不在程序所能支持序列");
        }
    }

}
