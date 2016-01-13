package com.std.account.api.impl;

import com.std.account.ao.IIdentityAO;
import com.std.account.api.AProcessor;
import com.std.account.common.IdCardChecker;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702004Req;
import com.std.account.dto.res.XN702004Res;
import com.std.account.enums.EIDKind;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 三方实名认证
 * @author: myb858 
 * @since: 2015年8月23日 下午2:00:12 
 * @history:
 */
public class XN801202 extends AProcessor {
    private IIdentityAO dentityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN702004Req xn702004Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702004Res(dentityAO.doIdentify(xn702004Req.getUserId(),
            xn702004Req.getRealName(), xn702004Req.getIdKind(),
            xn702004Req.getIdNo()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702004Req = JsonUtil.json2Bean(inputparams, XN702004Req.class);
        StringValidater.validateBlank(xn702004Req.getUserId(),
            xn702004Req.getRealName(), xn702004Req.getIdKind(),
            xn702004Req.getIdNo());
        if (!EIDKind.IDCard.getCode().equalsIgnoreCase(xn702004Req.getIdKind())) {
            throw new BizException("xn702000", "证件类型暂只支持身份证");
        }
        // 验证身份证号是否正确
        IdCardChecker idCardChecker = new IdCardChecker(xn702004Req.getIdNo());
        if (!idCardChecker.validate()) {
            throw new BizException("xn702000", "身份证号格式不正确");
        }
    }

}
