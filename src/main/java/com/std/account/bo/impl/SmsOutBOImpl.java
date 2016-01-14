package com.std.account.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.account.bo.ISmsOutBO;
import com.std.account.dto.req.XN799001Req;
import com.std.account.dto.req.XN799002Req;
import com.std.account.dto.res.XN799001Res;
import com.std.account.dto.res.XN799002Res;
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
            req.setContent(content);
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

    @Override
    public void checkSmsCaptcha(String mobile, String smsCaptcha, String bizType) {
        try {
            XN799002Req req = new XN799002Req();
            req.setMobile(mobile);
            req.setCaptcha(smsCaptcha);
            req.setBizType(bizType);
            BizConnecter.getBizData("799002", JsonUtils.object2Json(req),
                XN799002Res.class);
        } catch (Exception e) {
            logger.error("调用短信验证服务异常");
        }

    }
}
