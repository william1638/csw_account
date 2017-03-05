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
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IWeChatAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IWechatBO;
import com.std.account.common.JsonUtil;
import com.std.account.domain.Account;
import com.std.account.domain.CallbackResult;
import com.std.account.domain.CompanyChannel;
import com.std.account.domain.Jour;
import com.std.account.dto.res.XN802180Res;
import com.std.account.dto.res.XN802182Res;
import com.std.account.dto.res.XN805901Res;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EJourStatus;
import com.std.account.enums.ESysUser;
import com.std.account.exception.BizException;
import com.std.account.http.PostSimulater;
import com.std.account.util.HttpsUtil;
import com.std.account.util.wechat.TokenResponse;
import com.std.account.util.wechat.WXOrderQuery;
import com.std.account.util.wechat.XMLUtil;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:21:03 
 * @history:
 */
@Service
public class WeChatAOImpl implements IWeChatAO {

    @Autowired
    IWechatBO wechatBO;

    @Autowired
    IJourBO jourBO;

    @Autowired
    ICompanyChannelBO companyChannelBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IUserBO userBO;

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
        // 获取微信支付配置信息
        CompanyChannel companyChannel = getCompanyChannel(companyCode,
            systemCode, EChannelType.WeChat_APP.getCode());
        // 获取微信公众号支付prepayid
        String prepayId = wechatBO.getPrepayIdApp(companyChannel, bizNote,
            code, transAmount, ip);
        // 返回微信APP支付所需信息
        return wechatBO.getPayInfoApp(companyChannel, prepayId);
    }

    @Override
    @Transactional
    public XN802182Res getPrepayIdH5(String systemCode, String companyCode,
            String fromUserId, String toUserId, Long transAmount,
            String currency, String payGroup, String bizType, String bizNote) {
        Account account = accountBO.getAccountByUser(systemCode, fromUserId,
            ECurrency.CNY.getCode());
        // 本地系统落地流水信息
        String code = jourBO.addToChangeJour(systemCode,
            account.getAccountNumber(), EChannelType.WeChat_H5.getCode(),
            bizType, bizNote, transAmount, payGroup);
        // 获取微信公众号支付prepayid
        CompanyChannel companyChannel = getCompanyChannel(companyCode,
            systemCode, EChannelType.WeChat_H5.getCode());
        // 获取微信公众号支付prepayid
        XN805901Res user = userBO.getRemoteUser(fromUserId, fromUserId);
        if (StringUtils.isBlank(user.getOpenId())) {
            throw new BizException("xn000000", "获取用户openid失败");
        }
        String prepayId = wechatBO.getPrepayIdH5(companyChannel,
            user.getOpenId(), bizNote, code, transAmount, "192.168.1.1");
        // 返回微信APP支付所需信息
        return wechatBO.getPayInfoH5(companyChannel, code, prepayId);
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
        // 判断是否已回调
        if (!EJourStatus.todoCallBack.getCode().equals(jour.getStatus())) {
            throw new BizException("xn000000", "已回调成功");
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
    public CallbackResult doCallbackH5(String result) {
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
            if (!EJourStatus.todoCallBack.getCode().equals(jour.getStatus())) {
                throw new BizException("xn000000", "流水不处于待回调状态，重复回调");
            }
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
            // 系统账户加钱
            Account sysAccount = accountBO.getAccountByUser(systemCode,
                ESysUser.SYS_USER.getCode(), ECurrency.CNY.getCode());
            accountBO.transAmount(systemCode, sysAccount.getAccountNumber(),
                EChannelType.WeChat_H5, wechatOrderNo, jour.getTransAmount(),
                jour.getBizType(), jour.getBizNote());
            // ------------------------------
            // 处理业务完毕
            // ------------------------------
        }
        CompanyChannel companyChannel = getCompanyChannel(companyCode,
            systemCode, EChannelType.WeChat_H5.getCode());
        return new CallbackResult(isSucc, jour.getBizType(), jour.getCode(),
            jour.getPayGroup(), jour.getTransAmount(), systemCode, companyCode,
            companyChannel.getBackUrl());
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
            throw new BizException("xn000000", "回调业务biz异常");
        }
    }
}
