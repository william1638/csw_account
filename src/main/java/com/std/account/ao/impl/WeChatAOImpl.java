/**
 * @Title WeChatAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:21:03 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IWeChatAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.common.JsonUtil;
import com.std.account.domain.Account;
import com.std.account.domain.CallbackResult;
import com.std.account.domain.CompanyChannel;
import com.std.account.domain.Jour;
import com.std.account.dto.res.XN802180Res;
import com.std.account.dto.res.XN802182Res;
import com.std.account.dto.res.XN802183Res;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EWeChatType;
import com.std.account.exception.BizException;
import com.std.account.http.PostSimulater;
import com.std.account.util.HttpsUtil;
import com.std.account.util.wechat.MD5;
import com.std.account.util.wechat.MD5Util;
import com.std.account.util.wechat.OrderUtil;
import com.std.account.util.wechat.TokenResponse;
import com.std.account.util.wechat.WXOrderQuery;
import com.std.account.util.wechat.WXPrepay;
import com.std.account.util.wechat.XMLUtil;

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
     * @see com.std.account.ao.IWeChatAO#getPrepayIdApp(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public XN802180Res getPrepayIdApp(String systemCode, String companyCode,
            String userId, String bizType, String bizNote, Long transAmount,
            String currency, String payGroup, String ip) {
        Account account = accountBO.getAccountByUser(systemCode, userId,
            currency);
        // 本地系统落地流水信息
        String code = jourBO.addToChangeJour(systemCode,
            account.getAccountNumber(), EChannelType.WeChat_APP.getCode(),
            bizType, bizNote, transAmount, payGroup);
        // 获取微信公众号支付prepayid
        CompanyChannel companyChannel = getCompanyChannel(companyCode,
            systemCode, EChannelType.WeChat_APP.getCode());
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid(companyChannel.getPrivateKey2());// 微信开放平台审核通过的应用APPID
        prePay.setMch_id(companyChannel.getChannelCompany()); // 商户号
        prePay.setBody(companyChannel.getCompanyName() + "-" + bizNote); // 商品描述
        prePay.setOut_trade_no(code); // 订单号
        prePay.setTotal_fee(Long.toString(transAmount / 10)); // 订单总金额，厘转化成分
        prePay.setSpbill_create_ip(ip); // 用户IP
        prePay.setTrade_type(EWeChatType.APP.getCode()); // 交易类型
        prePay.setNotify_url(companyChannel.getBackUrl());// 回调地址
        prePay.setPartnerKey(companyChannel.getPrivateKey1()); // 商户秘钥
        prePay.setAttach(systemCode + "||" + companyCode);
        String prepayId = prePay.submitXmlGetPrepayId();

        SortedMap<String, String> nativeObj = new TreeMap<String, String>();
        nativeObj.put("appid", companyChannel.getPrivateKey2());
        nativeObj.put("partnerid", companyChannel.getChannelCompany());
        nativeObj.put("prepayid", prepayId);
        nativeObj.put("package", "Sign=WXPay");
        Random random = new Random();
        String randomStr = MD5.GetMD5String(String.valueOf(random
            .nextInt(10000)));
        nativeObj.put("noncestr", MD5Util.MD5Encode(randomStr, "utf-8")
            .toLowerCase());
        nativeObj.put("timestamp", OrderUtil.GetTimestamp());
        nativeObj.put("sign",
            createSign(nativeObj, companyChannel.getPrivateKey1()));

        XN802180Res res = new XN802180Res();
        res.setJourCode(code);
        res.setAppId(nativeObj.get("appid"));
        res.setPartnerid(nativeObj.get("partnerid"));
        res.setPrepayId(nativeObj.get("prepayid"));
        res.setWechatPackage(nativeObj.get("package"));
        res.setNonceStr(nativeObj.get("noncestr"));
        res.setTimeStamp(nativeObj.get("timestamp"));
        res.setSign(nativeObj.get("sign"));
        return res;
    }

    @Override
    @Transactional
    public XN802182Res getPrepayIdH5(String systemCode, String companyCode,
            String openId, String userId, String bizType, String bizNote,
            String body, Long totalFee, String spbillCreateIp) {
        Account account = accountBO.getAccountByUser(systemCode, userId,
            ECurrency.CNY.getCode());

        XN802182Res res = new XN802182Res();
        // 本地系统落地流水信息
        String code = jourBO.addToChangeJour(systemCode,
            account.getAccountNumber(), EChannelType.WeChat_H5.getCode(),
            bizType, bizNote, totalFee, null);
        res.setJourCode(code);
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
        String prepayId = prePay.submitXmlGetPrepayId();

        SortedMap<String, String> nativeObj = new TreeMap<String, String>();
        nativeObj.put("appId", companyChannel.getPrivateKey2());
        nativeObj.put("timeStamp", OrderUtil.GetTimestamp());
        Random random = new Random();
        String randomStr = MD5.GetMD5String(String.valueOf(random
            .nextInt(10000)));
        nativeObj.put("nonceStr", MD5Util.MD5Encode(randomStr, "utf-8")
            .toLowerCase());
        nativeObj.put("package", "prepay_id=" + prepayId);
        nativeObj.put("signType", "MD5");
        nativeObj.put("paySign",
            createSign(nativeObj, companyChannel.getPrivateKey1()));

        res.setPrepayId(prepayId);
        res.setAppId(nativeObj.get("appId"));
        res.setTimeStamp(nativeObj.get("timeStamp"));
        res.setNonceStr(nativeObj.get("nonceStr"));
        res.setWechatPackage(nativeObj.get("package"));
        res.setSignType(nativeObj.get("signType"));
        res.setPaySign(nativeObj.get("paySign"));
        return res;
    }

    @Override
    public CallbackResult doCallbackAPP(String result) {
        String systemCode = null;
        String companyCode = null;
        String wechatOrderNo = null;
        Jour jour = null;
        Map<String, String> map = null;
        try {
            map = XMLUtil.doXMLParse(result);
            String attach = map.get("attach");
            String[] codes = attach.split("\\|\\|");
            systemCode = codes[0];
            companyCode = codes[1];
            wechatOrderNo = map.get("transaction_id");
            jour = jourBO.getJour(map.get("out_trade_no"), systemCode);
        } catch (JDOMException | IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }
        // 此处调用订单查询接口验证是否交易成功
        boolean isSucc = reqOrderquery(map, EChannelType.WeChat_APP.getCode());
        // 支付成功，商户处理后同步返回给微信参数
        if (!isSucc) {
            // 支付失败
            System.out.println("支付失败");
            jourBO.callBackChangeJour(jour.getCode(), EBoolean.NO.getCode(),
                "WeChat_APP", "微信APP支付后台自动回调", wechatOrderNo);
        } else {
            System.out.println("===============付款成功==============");
            // ------------------------------
            // 处理业务开始
            // ------------------------------
            jourBO.callBackChangeJour(map.get("out_trade_no"),
                EBoolean.YES.getCode(), "WeChat_APP", "微信APP支付后台自动回调",
                wechatOrderNo);
            // ------------------------------
            // 处理业务完毕
            // ------------------------------
        }
        CompanyChannel companyChannel = getCompanyChannel(companyCode,
            systemCode, EChannelType.WeChat_APP.getCode());
        return new CallbackResult(isSucc, jour.getBizType(), jour.getCode(),
            jour.getPayGroup(), jour.getTransAmount(), systemCode, companyCode,
            companyChannel.getBackUrl());
    }

    @Override
    public XN802183Res doCallbackH5(String result) {
        XN802183Res res = new XN802183Res();
        String systemCode = null;
        String companyCode = null;
        String wechatOrderNo = null;
        Jour jour = null;
        Map<String, String> map = null;
        try {
            map = XMLUtil.doXMLParse(result);
            String attach = map.get("attach");
            String[] codes = attach.split("\\|\\|");
            systemCode = codes[0];
            companyCode = codes[1];
            wechatOrderNo = map.get("transaction_id");
            jour = jourBO.getJour(map.get("out_trade_no"), systemCode);
        } catch (JDOMException | IOException e) {
            throw new BizException("xn000000", "回调结果XML解析失败");
        }

        // 此处调用订单查询接口验证是否交易成功
        boolean isSucc = reqOrderquery(map, EChannelType.WeChat_H5.getCode());
        // 支付成功，商户处理后同步返回给微信参数
        if (!isSucc) {
            // 支付失败
            System.out.println("支付失败");
            jourBO.callBackChangeJour(jour.getCode(), EBoolean.NO.getCode(),
                "WeChat_H5", "微信公众号支付后台自动回调", wechatOrderNo);
        } else {
            System.out.println("===============付款成功==============");
            // ------------------------------
            // 处理业务开始
            // ------------------------------
            jourBO.callBackChangeJour(jour.getCode(), EBoolean.YES.getCode(),
                "WeChat_H5", "微信公众号支付后台自动回调", wechatOrderNo);
            // ------------------------------
            // 处理业务完毕
            // ------------------------------
        }
        res.setIsSuccess(isSucc);
        res.setJourCode(jour.getCode());
        res.setBizType(jour.getBizType());
        return res;
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

    public boolean reqOrderquery(Map<String, String> map, String channelType) {
        System.out.println("******* 开始订单查询 ******");
        WXOrderQuery orderQuery = new WXOrderQuery();
        orderQuery.setAppid(map.get("appid"));
        orderQuery.setMch_id(map.get("mch_id"));
        orderQuery.setTransaction_id(map.get("transaction_id"));
        orderQuery.setOut_trade_no(map.get("out_trade_no"));
        orderQuery.setNonce_str(map.get("nonce_str"));

        String attach = map.get("attach");
        System.out.println("attcah=" + attach);
        String[] codes = attach.split("\\|\\|");
        System.out.println("companyCode=" + codes[0] + " systemCode="
                + codes[1]);
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
                    System.out.println("******* 开订单查询结束，结果正确 ******");
                    return true;
                }
            }
        }
        System.out.println("******* 开订单查询结束，结果不正确 ******");
        return false;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public String createSign(SortedMap<String, String> packageParams,
            String AppKey) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + AppKey);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        return sign;
    }

    @Override
    public void doBizCallback(CallbackResult callbackResult) {
        try {
            Properties formProperties = new Properties();
            formProperties.put("isSuccess", callbackResult.isSuccess());
            formProperties.put("systemCode", callbackResult.getSystemCode());
            formProperties.put("companyCode", callbackResult.getCompanyCode());
            formProperties.put("payGroup", callbackResult.getPayGroup());
            formProperties.put("payCode", callbackResult.getJourCode());
            formProperties.put("bizType", callbackResult.getBizType());
            formProperties.put("transAmount", callbackResult.getTransAmount());
            PostSimulater.requestPostForm(callbackResult.getUrl(),
                formProperties);
        } catch (Exception e) {
            throw new BizException("xn000000", "链接请求超时，请联系管理员");
        }
    }

}
