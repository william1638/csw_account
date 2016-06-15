package com.std.account.bo.impl;

import org.springframework.stereotype.Component;

import com.std.account.bo.IUserBO;
import com.std.account.dto.req.XN805902Req;
import com.std.account.http.BizConnecter;
import com.std.account.http.JsonUtils;

@Component
public class UserBOImpl implements IUserBO {

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        XN805902Req req = new XN805902Req();
        req.setTokenId(userId);
        req.setUserId(userId);
        req.setTradePwd(tradePwd);
        BizConnecter.getBizData("805902", JsonUtils.object2Json(req),
            Object.class);
    }
}
