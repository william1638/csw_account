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
import java.util.Map;

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
import com.std.account.util.wechat.WXOrderQuery;
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
    public String getPrepayIdApp(String systemCode, String companyCode,
            String accountNumber, String bizType, String bizNote, String body,
            Long totalFee, String spbillCreateIp) {
        // 本地系统落地流水信息
        String code = jourBO.addToChangeJour(systemCode, accountNumber,
            EChannelType.WeChat_APP.getCode(), bizType, bizNote, totalFee);
        // 获取微信公众号支付prepayid
        CompanyChannel companyChannel = getCompanyChannel(companyCode,
            systemCode, EChannelType.WeChat_APP.getCode());
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid(companyChannel.getPrivateKey2());// 微信开放平台审核通过的应用APPID
        prePay.setMch_id(companyChannel.getChannelCompany()); // 商户号
        prePay.setBody(body); // 商品描述
        prePay.setOut_trade_no(code); // 订单号
        prePay.setTotal_fee(Long.toString(totalFee / 10)); // 订单总金额，厘转化成分
        prePay.setSpbill_create_ip(spbillCreateIp); // 用户IP
        prePay.setTrade_type(EWeChatType.APP.getCode()); // 交易类型
        prePay.setNotify_url(companyChannel.getBackUrl());// 回调地址
        prePay.setPartnerKey(companyChannel.getPrivateKey1()); // 商户秘钥
        prePay.setAttach(companyCode + "||" + systemCode);
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
            systemCode, EChannelType.WeChat_H5.getCode());
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
            String systemCode, String channelType) {
        CompanyChannel condition = new CompanyChannel();
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        condition.setChannelType(channelType);
        List<CompanyChannel> list = companyChannelBO
            .queryCompanyChannelList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "获取支付渠道配置失败，请仔细检查配置信息");
        }
        return list.get(0);
    }

    @Override
    public boolean reqOrderquery(Map<String, String> map, String channelType) {
        WXOrderQuery orderQuery = new WXOrderQuery();
        orderQuery.setAppid(map.get("appid"));
        orderQuery.setMch_id(map.get("mch_id"));
        orderQuery.setTransaction_id(map.get("transaction_id"));
        orderQuery.setOut_trade_no(map.get("out_trade_no"));
        orderQuery.setNonce_str(map.get("nonce_str"));

        String attach = map.get("attach");
        String[] codes = attach.split("||");
        CompanyChannel companyChannel = getCompanyChannel(codes[0], codes[1],
            channelType);

        // 此处需要密钥PartnerKey，此处直接写死，自己的业务需要从持久化中获取此密钥，否则会报签名错误
        orderQuery.setPartnerKey(companyChannel.getPrivateKey1());

        Map<String, String> orderMap = orderQuery.reqOrderquery();
        // 此处添加支付成功后，支付金额和实际订单金额是否等价，防止钓鱼
        if (orderMap.get("return_code") != null
                && orderMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
            if (orderMap.get("trade_state") != null
                    && orderMap.get("trade_state").equalsIgnoreCase("SUCCESS")) {
                String total_fee = map.get("total_fee");
                String order_total_fee = map.get("total_fee");
                if (Integer.parseInt(order_total_fee) >= Integer
                    .parseInt(total_fee)) {
                    return true;
                }
            }
        }
        return false;
    }
}
