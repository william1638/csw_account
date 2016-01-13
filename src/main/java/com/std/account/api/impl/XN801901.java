package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IBankCardAO;
import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PropertiesUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.BankCard;
import com.std.account.domain.User;
import com.std.account.dto.req.XN702981Req;
import com.std.account.dto.res.XN702981Res;
import com.std.account.enums.EBoolean;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 获取用户信息
 * @author: myb858 
 * @since: 2015年11月1日 下午2:56:28 
 * @history:
 */
public class XN801901 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private IBankCardAO bankCardAO = SpringContextHolder
        .getBean(IBankCardAO.class);

    private XN702981Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = req.getUserId();
        User user = userAO.doGetUser(userId);
        XN702981Res res = new XN702981Res();
        if (user != null) {
            res.setUserId(userId);
            res.setPhoto(PropertiesUtil.getProperty("PHOTO_URL"));
            res.setLoginName(user.getMobile());
            res.setStatus(user.getStatus());
            res.setMobileFlag(EBoolean.YES.getCode());
            if (StringUtils.isNotBlank(user.getIdNo())) {
                res.setIdentityFlag(EBoolean.YES.getCode());
            } else {
                res.setIdentityFlag(EBoolean.NO.getCode());
            }
            if (StringUtils.isNotBlank(user.getTradePwdStrength())) {
                res.setTradepwdFlag(EBoolean.YES.getCode());
            } else {
                res.setTradepwdFlag(EBoolean.NO.getCode());
            }
            if (StringUtils.isNotBlank(user.getIdNo())) {
                res.setIdentityFlag(EBoolean.YES.getCode());
            } else {
                res.setIdentityFlag(EBoolean.NO.getCode());
            }
            BankCard bankcard = bankCardAO.getBankCard(userId);
            if (bankcard != null
                    && StringUtils.isNotBlank(bankcard.getBankCardNo())) {
                res.setBankcardFlag(EBoolean.YES.getCode());
            } else {
                res.setBankcardFlag(EBoolean.NO.getCode());
            }
            res.setIdKind(user.getIdKind());
            res.setIdNo(user.getIdNo());
            res.setRealName(user.getRealName());
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702981Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId());
    }

}
