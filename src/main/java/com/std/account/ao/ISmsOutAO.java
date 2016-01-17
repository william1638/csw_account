package com.std.account.ao;

public interface ISmsOutAO {

    public void sendAppSms(String userId, String content);

    public void sendCaptcha(String mobile, String bizType);
}
