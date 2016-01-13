package com.std.account.api.impl;

import com.std.account.ao.IIdentityAO;
import com.std.account.api.AProcessor;
import com.std.account.common.IdCardChecker;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702010Req;
import com.std.account.dto.res.XN702010Res;
import com.std.account.enums.EIDKind;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 申请人工实名认证
 * @author: myb858 
 * @since: 2015年11月1日 下午3:30:32 
 * @history:
 */
public class XN702010 extends AProcessor {
    private IIdentityAO dentityAO = SpringContextHolder
        .getBean(IIdentityAO.class);

    private XN702010Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702010Res(dentityAO.doSaveUserPicture(req.getUserId(),
            req.getRealName(), req.getIdKind(), req.getIdNo(), req.getIdPic1(),
            req.getIdPic2(), req.getIdUserPic()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702010Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getRealName(),
            req.getIdKind(), req.getIdNo(), req.getIdPic1(), req.getIdPic2(),
            req.getIdUserPic());
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
