package com.std.account.api.impl;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.dto.req.XN702517Req;
import com.std.account.dto.res.XN702517Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 易宝一键支付回调
 * @author: myb858 
 * @since: 2015年11月16日 下午5:11:59 
 * @history:
 */
public class XN702517 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN702517Req xn702517Req = null;

    @Override
    public XN702517Res doBusiness() throws BizException {
        return new XN702517Res(cqOrderAO.doCallbackInstantPay(
            xn702517Req.getData(), xn702517Req.getEncryptkey()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702517Req = JsonUtil.json2Bean(inputparams, XN702517Req.class);
    }
}
