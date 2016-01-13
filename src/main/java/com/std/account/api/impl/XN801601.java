package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.IdCardChecker;
import com.std.account.common.JsonUtil;
import com.std.account.common.PhoneUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702381Req;
import com.std.account.dto.res.XN702381Res;
import com.std.account.enums.EIDKind;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 代注册
 * @author: myb858 
 * @since: 2015年11月10日 下午12:59:10 
 * @history:
 */
public class XN801601 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702381Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702381Res(userAO.doAddFaRen(req.getMobile(),
            req.getUserReferee(), req.getRealName(), req.getIdKind(),
            req.getIdNo(), req.getBankCode(), req.getBankName(),
            req.getBankCardNo(), req.getSubbranch(), req.getBindMobile()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702381Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getRealName(),
            req.getIdKind(), req.getIdNo(), req.getBankCode(),
            req.getBankName(), req.getBankCardNo());
        // 判断格式
        PhoneUtil.checkMobile(req.getMobile());
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
