/**
 * @Title WeChatAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:21:03 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IWeChatAO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.common.JsonUtil;
import com.std.account.core.OrderNoGenerater;
import com.std.account.domain.CompanyChannel;
import com.std.account.exception.BizException;
import com.std.account.util.HttpsUtil;
import com.std.account.util.wechat.TokenResponse;
import com.std.account.util.wechat.WXPrepay;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:21:03 
 * @history:
 */
@Service
public class WeChatAOImpl implements IWeChatAO {

    @Autowired
    IJourBO jourBO;

    @Autowired
    ICompanyChannelBO companyChannelBO;

    /** 
     * @see com.std.account.ao.IWeChatAO#getPrepayId(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public String getPrepayIdApp(String systemCode, String body, Long totalFee,
            String spbillCreateIp, String notifyUrl) {
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid("wx3eb3d4d796093674");// 骑来骑去应用ID
        prePay.setMch_id(""); // 商户号
        prePay.setBody(""); // 商品描述
        prePay.setOut_trade_no(""); // 订单号
        prePay.setTotal_fee(""); // 订单总金额
        prePay.setSpbill_create_ip(""); // 用户IP
        prePay.setTrade_type("APP"); // 交易类型
        prePay.setNotify_url("http://www.weixin.qq.com/wxpay/pay.php");// 回调地址
        return prePay.submitXmlGetPrepayId();
    }

    @Override
    public String getPrepayIdH5(String systemCode, String body, Long totalFee,
            String spbillCreateIp, String notifyUrl) {
        CompanyChannel companyChannel = companyChannelBO.getCompanyChannel(1L);
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid("wx8bc03dd744895352");// 微信支付分配的公众账号ID
        prePay.setMch_id("1400666002"); // 商户号
        prePay.setBody(body); // 商品描述
        prePay.setOut_trade_no(OrderNoGenerater.generate("DD")); // 订单号
        prePay.setTotal_fee(Long.toString(totalFee)); // 订单总金额
        prePay.setSpbill_create_ip(spbillCreateIp); // 用户IP
        prePay.setTrade_type("JSAPI"); // 交易类型
        prePay.setNotify_url(notifyUrl);// 回调地址
        prePay.setPartnerKey("r2jgDFSdiikklwlllejlwjio3242342n");
        prePay.setOpenid("ojdtdv71yky2o8yofg3TkSyog93E");
        return prePay.submitXmlGetPrepayId();
    }

    @Override
    public int doCallbackH5(String code, String callbackResult) {
        return jourBO.callBackChangeJour(code, callbackResult, "", "");

    }

    @Override
    public String getAccessToken(String systemCode) {
        String accessToken = null;
        String postUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx8bc03dd744895352&secret=44ebf0ef908dc54656573625a579ea82";
        String response = null;
        try {
            response = new String(HttpsUtil.post(postUrl, "", "UTF-8"));
            TokenResponse tokenResponse = JsonUtil.json2Bean(response,
                TokenResponse.class);
            accessToken = tokenResponse.getAccess_token();
        } catch (Exception e) {
            throw new BizException("xn000000", "获取微信accessToekn失败，请检查参数");
        }
        return accessToken;
    }
}
