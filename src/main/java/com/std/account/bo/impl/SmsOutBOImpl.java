package com.std.account.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.std.account.bo.ISmsOutBO;
import com.std.account.dto.req.XN799001Req;
import com.std.account.dto.req.XN799002Req;
import com.std.account.dto.req.XN799003Req;
import com.std.account.dto.res.XN799001Res;
import com.std.account.dto.res.XN799002Res;
import com.std.account.dto.res.XN799003Res;
import com.std.account.http.BizConnecter;
import com.std.account.http.JsonUtils;

@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public void sendSmsOut(String mobile, String content, String bizType,
            String remark) {
        try {
            XN799001Req req = new XN799001Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setBizType(bizType);
            req.setRemark(remark);
            BizConnecter.getBizData("799001", JsonUtils.object2Json(req),
                XN799001Res.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }

    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType) {
        try {
            XN799002Req req = new XN799002Req();
            req.setMobile(mobile);
            req.setCaptcha(captcha);
            req.setBizType(bizType);
            BizConnecter.getBizData("799002", JsonUtils.object2Json(req),
                XN799002Res.class);
        } catch (Exception e) {
            logger.error("调用短信验证服务异常");
        }

    }

    @Override
    public void sendCaptcha(String mobile, String bizType) {
        try {
            XN799003Req req = new XN799003Req();
            req.setMobile(mobile);
            req.setBizType(bizType);
            BizConnecter.getBizData("799003", JsonUtils.object2Json(req),
                XN799003Res.class);
        } catch (Exception e) {
            logger.error("调用短信验证服务异常");
        }
    }
}
