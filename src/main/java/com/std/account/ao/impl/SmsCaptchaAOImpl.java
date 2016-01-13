package com.std.account.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.ISmsCaptchaAO;
import com.std.account.bo.ISmsCaptchaBO;
import com.std.account.bo.ISmsOutBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.base.Paginable;
import com.std.account.common.DateUtil;
import com.std.account.core.RandomGenerater;
import com.std.account.domain.SmsCaptcha;
import com.std.account.enums.ESmsBizType;
import com.std.account.enums.ESmsStatus;
import com.std.account.exception.BizException;

@Service
public class SmsCaptchaAOImpl implements ISmsCaptchaAO {

    @Autowired
    protected IUserBO userBO;

    @Autowired
    ISmsCaptchaBO smsCaptchaBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Override
    @Transactional
    public boolean sentSmsCaptcha(String mobile, String bizType) {
        // 安全控制,防止恶意多次发送
        safeCheck(mobile, bizType);

        String smsCaptcha = RandomGenerater.generate4();
        // 发送短信
        boolean flag = smsOutBO.sendSmsOut(mobile, smsCaptcha,
            ESmsBizType.YZM.getCode(), ESmsBizType.YZM.getValue());
        // 存db
        SmsCaptcha data = new SmsCaptcha();
        data.setMobile(mobile);
        data.setBizType(bizType);
        data.setSmsCaptcha(smsCaptcha);
        if (flag) {
            data.setStatus(ESmsStatus.SENT_YES.getCode());
        } else {
            data.setStatus(ESmsStatus.SENT_NO.getCode());
        }
        Date now = new Date();
        data.setCreateDatetime(now);
        data.setSentDatetime(now);
        data.setInvalidDatetime(DateUtil.getRelativeDate(now, 600));// 60秒后失效
        smsCaptchaBO.saveSentSmsCaptcha(data);
        return true;
    }

    @Override
    public Paginable<SmsCaptcha> querySmsCaptchaPage(int start, int limit,
            SmsCaptcha condition) {
        return smsCaptchaBO.getPaginable(start, limit, condition);
    }

    private void safeCheck(String mobile, String bizType) {
        int min = 60;
        int count = 10;

        SmsCaptcha condition = new SmsCaptcha();
        condition.setMobile(mobile);
        condition.setBizType(bizType);
        String now = DateUtil.getToday(DateUtil.DATA_TIME_PATTERN_1);
        Date start = DateUtil.getRelativeDate(
            DateUtil.strToDate(now, DateUtil.DATA_TIME_PATTERN_1), -60 * min);
        condition.setCreateDatetimeStart(start);
        condition.setCreateDatetimeEnd(DateUtil.strToDate(now,
            DateUtil.DATA_TIME_PATTERN_1));

        if (smsCaptchaBO.getTotalCount(condition) >= count) {
            throw new BizException("XN000000", "您的操作太过频繁，请稍后再试");
        }
    }

}
