package com.std.account.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.account.bo.ISmsOutBO;
import com.std.account.common.PhoneUtil;
import com.std.account.dto.req.XN799001Req;
import com.std.account.dto.res.XN799001Res;
import com.std.account.enums.ESmsBizType;
import com.std.account.http.BizConnecter;
import com.std.account.http.JsonUtils;

@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public boolean sendSmsOut(String mobile, String content, String bizType,
            String remark) {
        XN799001Res res = new XN799001Res();
        try {
            XN799001Req req = new XN799001Req();
            req.setMobile(mobile);
            if (ESmsBizType.YZM.getCode().equalsIgnoreCase(bizType)) {
                req.setContent("尊敬的" + PhoneUtil.hideMobile(mobile)
                        + "用户, 您的验证码为" + content + "，请妥善保管此验证码，切勿泄露给他人。");
            } else {
                req.setContent(content);
            }
            req.setBizType(bizType);
            req.setRemark(remark);
            res = BizConnecter.getBizData("799001", JsonUtils.object2Json(req),
                XN799001Res.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
        if (res.getId() != null && res.getId() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
