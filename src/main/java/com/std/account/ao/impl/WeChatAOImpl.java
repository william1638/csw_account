/**
 * @Title WeChatAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:21:03 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IWeChatAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.common.JsonUtil;
import com.std.account.domain.CompanyChannel;
import com.std.account.enums.EChannelType;
import com.std.account.enums.EWeChatType;
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

    @Autowired
    IAccountBO accountBO;

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
    @Transactional
    public String getPrepayIdH5(String systemCode, String companyCode,
            String openId, String accountNumber, String bizType,
            String bizNote, String body, Long totalFee, String spbillCreateIp) {
        // 本地系统落地流水信息
        String code = jourBO.addToChangeJour(systemCode, accountNumber,
            EChannelType.WeChat_H5.getCode(), bizType, bizNote, totalFee);
        // 获取微信公众号支付prepayid
        CompanyChannel companyChannel = getCompanyChannel(companyCode,
            systemCode);
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid(companyChannel.getPrivateKey2());// 微信支付分配的公众账号ID
        prePay.setMch_id(companyChannel.getChannelCompany()); // 商户号
        prePay.setBody(body); // 商品描述
        prePay.setOut_trade_no(code); // 订单号
        prePay.setTotal_fee(Long.toString(totalFee / 10)); // 订单总金额，厘转化成分
        prePay.setSpbill_create_ip(spbillCreateIp); // 用户IP
        prePay.setTrade_type(EWeChatType.JSAPI.getCode()); // 交易类型
        prePay.setNotify_url(companyChannel.getBackUrl());// 回调地址
        prePay.setPartnerKey(companyChannel.getPrivateKey1()); // 商户秘钥
        prePay.setOpenid(openId); // 支付者openid
        prePay.setAttach(companyCode + "||" + systemCode);
        return prePay.submitXmlGetPrepayId();
    }

    @Override
    public int doCallbackH5(String code, String callbackResult) {
        return jourBO.callBackChangeJour(code, callbackResult, "WeChat_H5",
            "微信公众号支付后台自动回调");
    }

    @Override
    public String getAccessToken(String appId, String appSecret) {
        String accessToken = null;
        String postUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + appId + "&secret=" + appSecret;
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

    @Override
    public CompanyChannel getCompanyChannel(String companyCode,
            String systemCode) {
        CompanyChannel condition = new CompanyChannel();
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        List<CompanyChannel> list = companyChannelBO
            .queryCompanyChannelList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "获取支付渠道配置失败，请仔细检查配置信息");
        }
        return list.get(0);
    }
}
