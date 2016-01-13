package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.ISmsCaptchaBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.ISmsCaptchaDAO;
import com.std.account.domain.SmsCaptcha;
import com.std.account.enums.ESmsStatus;
import com.std.account.exception.BizException;

/**
 * 短信验证码
 * @author: xieyj 
 * @since: 2015-3-10 下午8:38:53 
 * @history:
 */
@Component
public class SmsCaptchaBOImpl extends PaginableBOImpl<SmsCaptcha> implements
        ISmsCaptchaBO {
    @Autowired
    private ISmsCaptchaDAO smsCaptchaDAO;

    @Override
    public int saveSentSmsCaptcha(SmsCaptcha data) {
        int count = 0;
        if (data != null) {
            count = smsCaptchaDAO.insert(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.data.ISmsCaptchaBO#checkSmsCaptcha(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void checkSmsCaptcha(String mobile, String captcha, String bizType) {
        SmsCaptcha condition = new SmsCaptcha();
        condition.setMobile(mobile);
        condition.setSmsCaptcha(captcha);
        condition.setBizType(bizType);
        condition.setStatus(ESmsStatus.SENT_YES.getCode());
        condition.setInvalidDatetime(new Date());
        condition.setCheckTimes(1);
        List<SmsCaptcha> list = smsCaptchaDAO.selectList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("li01004", "验证码错误");
        }
        for (SmsCaptcha ele : list) {
            ele.setStatus(ESmsStatus.CHECKED.getCode());
            ele.setCheckDatetime(new Date());
            ele.setCheckTimes(ele.getCheckTimes() + 1);
            smsCaptchaDAO.updateCheckInfo(ele);
        }
    }

    @Override
    public SmsCaptcha getSmsCaptchaBO(Long id) {
        SmsCaptcha data = null;
        if (id != null && id != 0) {
            SmsCaptcha condition = new SmsCaptcha();
            condition.setId(id);
            data = smsCaptchaDAO.select(condition);
        }
        return data;
    }

    @Override
    public List<SmsCaptcha> querySmsCaptchaBOList(SmsCaptcha condition) {
        return smsCaptchaDAO.selectList(condition);
    }

}
