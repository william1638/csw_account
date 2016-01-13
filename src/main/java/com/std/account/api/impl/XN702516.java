package com.std.account.api.impl;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.dto.req.XN702516Req;
import com.std.account.dto.res.XN702516Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 易宝回调解析
 * @author: myb858 
 * @since: 2015年10月22日 下午2:59:31 
 * @history:
 */
public class XN702516 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN702516Req xn702516Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702516Res(cqOrderAO.doCallbackChargeYeepay(
            xn702516Req.getP1MerId(), xn702516Req.getR0_Cmd(),
            xn702516Req.getR1_Code(), xn702516Req.getR2_TrxId(),
            xn702516Req.getR3_Amt(), xn702516Req.getR4_Cur(),
            xn702516Req.getR5_Pid(), xn702516Req.getR6_Order(),
            xn702516Req.getR7_Uid(), xn702516Req.getR8_MP(),
            xn702516Req.getR9_BType(), xn702516Req.getHmac()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702516Req = JsonUtil.json2Bean(inputparams, XN702516Req.class);
    }

}
