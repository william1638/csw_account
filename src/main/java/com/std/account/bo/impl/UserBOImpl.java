package com.std.account.bo.impl;

import org.springframework.stereotype.Component;

import com.std.account.bo.IUserBO;
import com.std.account.dto.req.XN001100Req;
import com.std.account.http.BizConnecter;
import com.std.account.http.JsonUtils;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:30 
 * @history:
 */
@Component
public class UserBOImpl implements IUserBO {

    /** 
     * @see com.xnjr.mall.bo.IUserBO#checkTradePwd(java.lang.String, java.lang.String)
     */
    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        XN001100Req req = new XN001100Req();
        req.setTokenId(userId);
        req.setUserId(userId);
        req.setTradePwd(tradePwd);
        BizConnecter.getBizData("001100", JsonUtils.object2Json(req),
            Object.class);
    }

}
