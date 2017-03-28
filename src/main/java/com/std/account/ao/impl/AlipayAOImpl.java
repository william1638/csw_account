/**
 * @Title AlipayAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2017年1月11日 下午8:56:56 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.WebUtils;
import com.std.account.ao.IAlipayAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.common.PropertiesUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Account;
import com.std.account.domain.CallbackResult;
import com.std.account.domain.CompanyChannel;
import com.std.account.domain.Jour;
import com.std.account.dto.res.XN802184Res;
import com.std.account.enums.EChannelType;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.http.PostSimulater;
import com.std.account.util.CalculationUtil;
import com.std.account.util.alipay.AlipayCore;

/** 
 * @author: haiqingzheng 
 * @since: 2017年1月11日 下午8:56:56 
 * @history:
 */
@Service
public class AlipayAOImpl implements IAlipayAO {
    static Logger logger = Logger.getLogger(AlipayAOImpl.class);

    public static final String CHARSET = "utf-8";

    @Autowired
    IJourBO jourBO;

    @Autowired
    ICompanyChannelBO companyChannelBO;

    @Autowired
    IAccountBO accountBO;

    /** 
     * @see com.std.account.ao.IAlipayAO#getPrepayIdApp(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
     */
    @Override
    public Object getSignedOrder(String systemCode, String companyCode,
            String userId, String bizType, String bizNote, String body,
            Long totalFee) {
        // 配置说明
        // channel_company —— 卖家支付宝用户号
        // private_key1 —— APP_PRIVATE_KEY，开发者应用私钥，由开发者自己生成
        // private_key2 —— ALIPAY_PUBLIC_KEY，支付宝公钥，由支付宝生成
        // private_key3 —— APP_ID，APPID即创建应用后生成
        CompanyChannel condition = new CompanyChannel();
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        condition.setChannelType(EChannelType.Alipay.getCode());
        List<CompanyChannel> list = companyChannelBO
            .queryCompanyChannelList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "获取支付渠道配置失败，请仔细检查配置信息");
        }
        CompanyChannel companyChannel = list.get(0);

        Account account = accountBO.getAccountByUser(userId,
            ECurrency.CNY.getCode());
        // 本地系统落地流水信息
        String code = jourBO.addToChangeJour(systemCode,
            account.getAccountNumber(), EChannelType.Alipay.getCode(), bizType,
            bizNote, totalFee, null);

        // 生成业务参数(bizContent)json字符串
        Map<String, String> bizParams = new HashMap<String, String>();
        bizParams.put("subject", body); // 商品的标题 例如：大乐透
        bizParams.put("out_trade_no", code); // 商户网站唯一订单号
        bizParams.put("total_amount", String.valueOf(totalFee / 1000.00)); // 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
        bizParams.put("product_code", "QUICK_MSECURITY_PAY"); // 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        String bizContentJson = JsonUtil.Object2Json(bizParams);
        logger.info("*****业务参数(bizContent)json字符串*****\n" + bizContentJson);

        // 1、按照key=value&key=value方式拼接的未签名原始字符串
        String appId = companyChannel.getPrivateKey3();
        String method = "alipay.trade.app.pay";
        String format = "JSON";
        String charset = CHARSET;
        String signType = "RSA2";
        String timestamp = DateUtil.dateToStr(new Date(),
            DateUtil.DATA_TIME_PATTERN_1);
        String version = "1.0";
        String notifyUrl = PropertiesUtil.Config.ALIPAY_APP_BACKURL;
        String bizContent = bizContentJson;

        String params = "app_id=%s&biz_content=%s&charset=%s&format=%s&method=%s&notify_url=%s&sign_type=%s&timestamp=%s&version=%s";
        params = String.format(params, appId, bizContent, charset, format,
            method, notifyUrl, signType, timestamp, version);
        logger.info("*****未签名原始字符串*****\n" + params);
        // 2、对原始字符串进行签名
        // 注意注意：获取所有请求参数，不包括字节类型参数，如文件、字节流，剔除sign字段，剔除值为空的参数，
        // 并按照第一个字符的键值ASCII码递增排序（字母升序排序），如果遇到相同字符则按照第二个字符的键值ASCII码递增排序，以此类推。
        String sign = null;
        try {
            sign = AlipaySignature.rsaSign(params,
                companyChannel.getPrivateKey1(), charset, signType);
            if (sign == null) {
                throw new BizException("xn000000", "原始字符串签名失败");
            }
            logger.info("*****原始字符串签名*****\n" + sign);
        } catch (AlipayApiException e) {
            throw new BizException("xn000000", "原始字符串签名出错");
        }

        // 3、对请求字符串的所有一级value（biz_content作为一个value）进行encode
        // try {
        appId = WebUtils.encode(appId);
        method = WebUtils.encode(method);
        format = WebUtils.encode(format);
        charset = WebUtils.encode(charset);
        signType = WebUtils.encode(signType);
        timestamp = WebUtils.encode(timestamp);
        version = WebUtils.encode(version);
        notifyUrl = WebUtils.encode(notifyUrl);
        bizContent = WebUtils.encode(bizContent);
        sign = WebUtils.encode(sign);
        String paramsEncoded = "app_id=%s&method=%s&format=%s&charset=%s&sign_type=%s&timestamp=%s&version=%s&notify_url=%s&biz_content=%s&sign=%s";
        paramsEncoded = String.format(paramsEncoded, appId, method, format,
            charset, signType, timestamp, version, notifyUrl, bizContent, sign);
        logger.info("*****签名Encode后的请求字符串*****\n" + paramsEncoded);

        XN802184Res res = new XN802184Res();
        res.setJourCode(code);
        res.setSignOrder(paramsEncoded);
        return res;
        // finalParamJson = URLEncoder.encode(params, CHARSET);
        // } catch (UnsupportedEncodingException e) {
        // throw new BizException("xn000000", "请求字符串encode出错");
        // }

        // // 实例化客户端
        // AlipayClient alipayClient = new DefaultAlipayClient(
        // "https://openapi.alipaydev.com/gateway.do",
        // companyChannel.getPrivateKey3()/* APPID即创建应用后生成 */,
        // companyChannel.getPrivateKey1()/* 开发者应用私钥 */, "json", "UTF-8",
        // companyChannel.getPrivateKey2()/* 支付宝公钥 */, "RSA2");
        // //
        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        // AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // // SDK已经封装掉了公共参数，这里只需要传入业务参数
        // // 此次只是参数展示，未进行字符串转义，实际情况下请转义
        // try {
        // request.setBizContent(URLEncoder.encode(bizContentJson, CHARSET));
        // } catch (UnsupportedEncodingException e1) {
        // e1.printStackTrace();
        // }
        // AlipayTradeAppPayResponse response;
        // try {
        // response = alipayClient.execute(request);
        // // 调用成功，则处理业务逻辑
        // if (response.isSuccess()) {
        // logger.info("*****调用成功*****");
        // }
        // } catch (AlipayApiException e) {
        // e.printStackTrace();
        // }

    }

    /**
     * @see com.std.account.ao.IAlipayAO#doCallbackAPP(java.lang.String)
     */
    @Override
    public CallbackResult doCallbackAPP(String result) {
        String systemCode = "CD-CZH000001";
        String companyCode = "CD-CZH000001";
        // 目前只有正汇钱包使用支付宝，暂时写死 todo：如何判断公司编号和系统编号???
        CompanyChannel condition = new CompanyChannel();
        condition.setCompanyCode(companyCode);
        condition.setSystemCode(systemCode);
        condition.setChannelType(EChannelType.Alipay.getCode());
        List<CompanyChannel> list = companyChannelBO
            .queryCompanyChannelList(condition);
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "获取支付渠道配置失败，请仔细检查配置信息");
        }
        CompanyChannel companyChannel = list.get(0);

        try {
            // 参数进行url_decode
            String params = URLDecoder.decode(result, CHARSET);
            // 将异步通知中收到的待验证所有参数都存放到map中
            Map<String, String> paramsMap = split(params);
            // 过滤+排序
            Map<String, String> filterMap = AlipayCore.paraFilter(paramsMap);
            String content = AlipayCore.createLinkString(filterMap);
            // 拿到签名
            String sign = paramsMap.get("sign");
            filterMap.put("sign", sign);
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsa256CheckContent(content,
                sign, companyChannel.getPrivateKey2(), CHARSET);
            logger.info("验签结果：" + signVerified);
            boolean isSuccess = false;
            if (signVerified) {
                // TODO 验签成功后
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                String outTradeNo = paramsMap.get("out_trade_no");
                String totalAmount = paramsMap.get("total_amount");
                String sellerId = paramsMap.get("seller_id");
                String appId = paramsMap.get("app_id");
                Jour fromJour = jourBO.getJour(outTradeNo, systemCode);
                if (fromJour.getTransAmount().equals(
                    StringValidater.toLong(CalculationUtil.mult(totalAmount)))
                        && sellerId.equals(companyChannel.getChannelCompany())
                        && appId.equals(companyChannel.getPrivateKey3())) {
                    return new CallbackResult(isSuccess, fromJour.getBizType(),
                        fromJour.getCode(), fromJour.getPayGroup(),
                        fromJour.getTransAmount(), systemCode, companyCode,
                        companyChannel.getBackUrl());
                } else {
                    throw new BizException("xn000000", "校验通知数据的正确性失败，默认为非法回调");
                }
            } else {
                throw new BizException("xn000000", "验签失败，默认为非法回调");
            }

        } catch (UnsupportedEncodingException e1) {
            throw new BizException("xn000000", "回调参数url_decode异常");
        } catch (AlipayApiException e) {
            throw new BizException("xn000000", "支付结果通知验签异常");
        }

    }

    /**
     * @param urlparam 带分隔的url参数
     * @return
     */
    private Map<String, String> split(String urlparam) {
        Map<String, String> map = new HashMap<String, String>();
        String[] param = urlparam.split("&");
        for (String keyvalue : param) {
            String[] pair = keyvalue.split("=");
            if (pair.length == 2) {
                map.put(pair[0], WebUtils.decode(pair[1]));
            }
        }
        return map;
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
