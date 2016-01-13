package com.std.account.api.impl;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702983Req;
import com.std.account.dto.res.XN702983Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 转入转出(//第一步：你告诉我要转入转出多少:我来告诉你是否成功---密钥私钥)
 * @author: myb858 
 * @since: 2015年8月23日 下午4:57:36 
 * @history:
 */
public class XN702983 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN702983Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = req.getUserId();
        String zzNo = zzOrderAO.doTransfer(userId,
            StringValidater.toLong(req.getAmount()), req.getOppositeSystem(),
            req.getOppositeAccount(), req.getRemark());
        XN702983Res res = new XN702983Res();
        res.setUserId(userId);
        res.setZzNo(zzNo);
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702983Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId(),
            req.getOppositeSystem());
        StringValidater.validateAmount(req.getAmount());
    }

}
