/**
 * @Title CMBUtil.java 
 * @Package com.cdkj.zhpay.core 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年2月26日 下午5:57:27 
 * @version V1.0   
 */
package com.std.account.util.cmb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import com.std.account.core.OrderNoGenerater;
import com.std.account.enums.EGeneratePrefix;

/** 
 * @author: haiqingzheng 
 * @since: 2017年2月26日 下午5:57:27 
 * @history:
 */
public class CMBUtil {
    static Logger logger = Logger.getLogger(CMBUtil.class);

    public static String url = "http://192.168.1.186:8080";

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // query("CBN2017030118052141031");
        // query(pay());
        query("CBN2017030211461561666");

    }

    public static String pay() throws IOException {

        Map<String, String> bizParams = new HashMap<>();
        bizParams.put("BUSCOD", "N02031");
        CMBBizRequest cmbBizRequest1 = new CMBBizRequest();
        cmbBizRequest1.setBizName("SDKPAYRQX");
        cmbBizRequest1.setBizParams(bizParams);

        bizParams = new HashMap<>();
        bizParams.put("YURREF",
            OrderNoGenerater.generate(EGeneratePrefix.CMB_BIZ_NO.getCode())); // 业务参考号
        bizParams.put("DBTACC", "579900750510806"); // 付方帐号
        bizParams.put("DBTBBK", "57"); // 付方开户地区代码
        bizParams.put("TRSAMT", "1"); // 交易金额
        bizParams.put("CCYNBR", "10"); // 币种代码 币种暂时只支持10(人民币)
        bizParams.put("STLCHN", "N"); // 结算方式代码 N：普通 F：快速
        bizParams.put("NUSAGE", "测试"); // 用途
        bizParams.put("BNKFLG", "Y"); // 系统内外标志 Y：招行；N：非招行
        bizParams.put("CRTACC", "6225885867450132"); // 收方帐号
        bizParams.put("CRTNAM", "郑海清"); // 收方帐户名
        bizParams.put("CRTBNK", "招商银行"); // 收方开户行 跨行支付（BNKFLG=N）必填
        CMBBizRequest cmbBizRequest2 = new CMBBizRequest();
        cmbBizRequest2.setBizName("DCOPDPAYX");
        cmbBizRequest2.setBizParams(bizParams);

        List<CMBBizRequest> bizRequests = new ArrayList<>();
        bizRequests.add(cmbBizRequest1);
        bizRequests.add(cmbBizRequest2);

        CMBRequest cmbRequest = new CMBRequest();
        cmbRequest.setFunctionName("DCPAYMNT");
        cmbRequest.setDateType("2");
        cmbRequest.setLoginName("zhqbpay");
        cmbRequest.setBizRequests(bizRequests);

        String response = doPost(url, cmbRequest);
        // 过滤
        String RETCOD = Jsoup.parse(response).select("RETCOD").html();
        String REQSTS = Jsoup.parse(response).select("REQSTS").html();
        String RTNFLG = Jsoup.parse(response).select("RTNFLG").html();
        String YURREF = Jsoup.parse(response).select("YURREF").html();
        System.out.println("&*&*&* RETCOD=" + RETCOD + " REQSTS=" + REQSTS
                + " RTNFLG=" + RTNFLG);
        if ("0".equals(RETCOD)) {
            if ("FIN".equals(REQSTS) && "F".equals(RTNFLG)) {
                logger.info("支付失败");
            } else {
                logger.info("支付已被银行受理");
            }
        } else if ("-9".equals(RETCOD) || "-1".equals(RETCOD)) {
            logger.info("交易可疑，请查询支付结果");
        } else {
            logger.info("交易请求失败");
        }
        System.out.println("&*&*&*&*&*&YURREF=" + YURREF);
        return YURREF;
    }

    public static void query(String YURREF) throws IOException {
        // TODO Auto-generated method stub

        Map<String, String> bizParams = new HashMap<>();
        bizParams.put("BUSCOD", "N02031");
        bizParams.put("BGNDAT", "20170302");
        bizParams.put("ENDDAT", "20170302");
        bizParams.put("YURREF", YURREF);
        CMBBizRequest cmbBizRequest1 = new CMBBizRequest();
        cmbBizRequest1.setBizName("SDKPAYQYX");
        cmbBizRequest1.setBizParams(bizParams);

        List<CMBBizRequest> bizRequests = new ArrayList<>();
        bizRequests.add(cmbBizRequest1);

        CMBRequest cmbRequest = new CMBRequest();
        cmbRequest.setFunctionName("GetPaymentInfo");
        cmbRequest.setDateType("2");
        cmbRequest.setLoginName("zhqbpay");
        cmbRequest.setBizRequests(bizRequests);

        String response = doPost(url, cmbRequest);
        // 过滤
        String RETCOD = Jsoup.parse(response).select("RETCOD").html();
        String REQSTS = Jsoup.parse(response).select("REQSTS").html();
        String RTNFLG = Jsoup.parse(response).select("RTNFLG").html();
        String RTNNAR = Jsoup.parse(response).select("RTNNAR").html();
        System.out.println("&*&*&* RETCOD=" + RETCOD + " REQSTS=" + REQSTS
                + " RTNFLG=" + RTNFLG);
        if ("0".equals(RETCOD)) {
            if (!"FIN".equals(REQSTS)) {
                logger.info("该笔支付银行还在处理中");
            } else {
                if ("S".equals(RTNFLG)) {
                    logger.info("支付成功");
                } else if ("B".equals(RTNFLG)) {
                    logger.info("退票");
                } else {
                    logger.info("支付失败 " + RTNNAR);
                }
            }
        } else {
            logger.info("查询失败");
        }

    }

    public static String doPost(String requestUrl, CMBRequest request) {
        String response = null;
        // try {
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost(requestUrl);
        String xml = request.getRequestXML();
        StringEntity entity;
        try {
            entity = new StringEntity(xml, "GBK");
            httpPost.setEntity(entity);
            HttpResponse httpResponse;
            // post请求
            httpResponse = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                // 打印响应内容
                response = EntityUtils.toString(httpEntity, "GBK");
                System.out.println("***" + response);
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
