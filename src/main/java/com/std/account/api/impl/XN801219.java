package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.IdCardChecker;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801219Req;
import com.std.account.dto.res.XN801219Res;
import com.std.account.enums.EIDKind;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 实名认证+设置交易密码
 * @author: myb858 
 * @since: 2016年1月28日 下午3:33:40 
 * @history:
 */
public class XN801219 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801219Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doIdentifySetTradePwd(req.getUserId(), req.getIdKind(),
            req.getIdNo(), req.getRealName(), req.getTradePwd(),
            req.getSmsCaptcha());
        return new XN801219Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801219Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getIdKind(),
            req.getIdNo(), req.getRealName(), req.getTradePwd(),
            req.getSmsCaptcha());
        if (!EIDKind.IDCard.getCode().equalsIgnoreCase(req.getIdKind())) {
            throw new BizException("xn702000", "证件类型暂只支持身份证");
        }
        // 验证身份证号是否正确
        IdCardChecker idCardChecker = new IdCardChecker(req.getIdNo());
        if (!idCardChecker.validate()) {
            throw new BizException("xn702000", "身份证号格式不正确");
        }

    }

}
