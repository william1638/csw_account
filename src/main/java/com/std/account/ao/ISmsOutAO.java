package com.std.account.ao;

public interface ISmsOutAO {

    public boolean sendAppSms(String userId, String content);
}
