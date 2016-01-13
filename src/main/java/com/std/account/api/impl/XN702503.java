package com.std.account.api.impl;

import java.util.Map;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702503Req;
import com.std.account.dto.res.XN702503Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 线上充值1--易宝充值第一步：你给我必要信息，之后我返回给你一堆提交信息。你根据我给你的信息进行自动提交
 * @author: myb858 
 * @since: 2015年8月23日 下午4:42:01 
 * @history:
 */
public class XN702503 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN702503Req xn702503Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(xn702503Req.getAmount());
        Long fee = StringValidater.toLong(xn702503Req.getFee());
        Map<String, String> map = cqOrderAO.doChargeYeepay(
            xn702503Req.getP1MerId(), xn702503Req.getAccountNumber(), amount,
            fee, xn702503Req.getBankCode());
        XN702503Res res = new XN702503Res();
        res.setNodeAuthorizationURL(map.get("nodeAuthorizationURL"));
        res.setP0_Cmd(map.get("p0_Cmd"));
        res.setP1_MerId(map.get("p1_MerId"));
        res.setP2_Order(map.get("p2_Order"));
        res.setP3_Amt(map.get("p3_Amt"));
        res.setP4_Cur(map.get("p4_Cur"));
        res.setP5_Pid(map.get("p5_Pid"));
        res.setP6_Pcat(map.get("p6_Pcat"));
        res.setP7_Pdesc(map.get("p7_Pdesc"));
        res.setP8_Url(map.get("p8_Url"));
        res.setP9_SAF(map.get("p9_SAF"));
        res.setPa_MP(map.get("pa_MP"));
        res.setPd_FrpId(map.get("pd_FrpId"));
        res.setPr_NeedResponse(map.get("pr_NeedResponse"));
        res.setHmac(map.get("hmac"));
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702503Req = JsonUtil.json2Bean(inputparams, XN702503Req.class);
        StringValidater.validateBlank(xn702503Req.getP1MerId(),
            xn702503Req.getAccountNumber(), xn702503Req.getAmount(),
            xn702503Req.getFee());

    }
}
