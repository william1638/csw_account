package com.std.account.api.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IBankCardAO;
import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PropertiesUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.BankCard;
import com.std.account.domain.User;
import com.std.account.dto.req.XN801901Req;
import com.std.account.dto.res.XN801901Res;
import com.std.account.enums.EBankCardType;
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

    private XN801901Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String userId = req.getUserId();
        User user = userAO.doGetUser(userId);
        XN801901Res res = new XN801901Res();
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
            List<BankCard> bankcardList = bankCardAO.queryBankCardList(userId,
                EBankCardType.User.getCode());
            if (CollectionUtils.isNotEmpty(bankcardList)) {
                res.setBankcardFlag(EBoolean.YES.getCode());
            } else {
                res.setBankcardFlag(EBoolean.NO.getCode());
            }
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801901Req.class);
        StringValidater.validateBlank(req.getTokenId(), req.getUserId());
    }

}
