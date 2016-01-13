package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.ISmsOutAO;
import com.std.account.bo.ISmsOutBO;
import com.std.account.bo.IUserBO;
import com.std.account.domain.User;
import com.std.account.enums.ESmsBizType;
import com.std.account.exception.BizException;

@Service
public class SmsOutAOImpl implements ISmsOutAO {
    @Autowired
    protected IUserBO userBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Override
    public boolean sendAppSms(String userId, String content) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn702001", "用户不存在");
        }
        smsOutBO.sendSmsOut(user.getMobile(), content,
            ESmsBizType.APP.getCode(), ESmsBizType.APP.getValue());
        return true;
    }
}
