package com.std.account.bo;

import com.std.account.dto.res.XN805901Res;

public interface IUserBO {

    public void checkTradePwd(String userId, String tradePwd);

    public XN805901Res getRemoteUser(String tokenId, String userId);

    public void firstSetRelation(String tokenId, String userId);

    public void sendSms(String tokenId, String userId, String content);

}
