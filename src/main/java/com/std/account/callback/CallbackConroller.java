package com.std.account.callback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.std.account.ao.IWeChatAO;
import com.std.account.domain.CallbackResult;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午1:44:16 
 * @history:
 */
@Controller
public class CallbackConroller {

    private static Logger logger = Logger.getLogger(CallbackConroller.class);

    @Autowired
    IWeChatAO weChatAO;

    @RequestMapping("/wechat/app/callback")
    public synchronized void doCallbackZhpay(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        // 获取回调参数
        PrintWriter out = response.getWriter();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");
        logger.info("**** APP支付回调结果 ****：" + result);
        // 解析回调结果
        CallbackResult callbackResult = weChatAO.doCallbackAPP(result);
        // String jourCode = res.getJourCode();
        // String bizType = res.getBizType();
        // 通知业务biz，参数为 是否成功，金额，支付组号，业务类型

        String noticeStr = setXML("SUCCESS", "");
        out.print(new ByteArrayInputStream(noticeStr.getBytes(Charset
            .forName("UTF-8"))));
    }

    public String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }
}
