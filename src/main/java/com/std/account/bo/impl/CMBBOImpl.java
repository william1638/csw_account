/**
 * @Title CMBBOImpl.java 
 * @Package com.std.account.bo.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年3月1日 下午4:34:41 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import com.std.account.bo.ICMBBO;
import com.std.account.core.OrderNoGenerater;
import com.std.account.domain.CompanyChannel;
import com.std.account.enums.EGeneratePrefix;
import com.std.account.util.cmb.CMBBizRequest;
import com.std.account.util.cmb.CMBRequest;
import com.std.account.util.cmb.CMBUtil;

/** 
 * @author: haiqingzheng 
 * @since: 2017年3月1日 下午4:34:41 
 * @history:
 */
public class CMBBOImpl implements ICMBBO {

    static Logger logger = Logger.getLogger(CMBBOImpl.class);

    @Override
    public void pay(String accountNo, String accountName, String bankCode,
            Long amount, String remark, CompanyChannel companyChannel) {

        Map<String, String> bizParams = new HashMap<>();
        bizParams.put("BUSCOD", "N02031");
        CMBBizRequest cmbBizRequest1 = new CMBBizRequest();
        cmbBizRequest1.setBizName("SDKPAYRQX");
        cmbBizRequest1.setBizParams(bizParams);

        bizParams = new HashMap<>();
        bizParams.put("YURREF",
            OrderNoGenerater.generate(EGeneratePrefix.CMB_BIZ_NO.getCode())); // 业务参考号
        bizParams.put("DBTACC", companyChannel.getPrivateKey1()); // 付方帐号
        bizParams.put("DBTBBK", companyChannel.getPrivateKey2()); // 付方开户地区代码
        bizParams.put("TRSAMT", String.valueOf(amount / 1000.00)); // 交易金额
        bizParams.put("CCYNBR", "10"); // 币种代码 币种暂时只支持10(人民币)
        bizParams.put("STLCHN", "N"); // 结算方式代码 N：普通 F：快速
        bizParams.put("NUSAGE", remark); // 用途
        bizParams.put("BNKFLG", "Y"); // 系统内外标志 Y：招行；N：非招行
        bizParams.put("CRTACC", accountNo); // 收方帐号
        bizParams.put("CRTNAM", accountNo); // 收方帐户名
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
        cmbRequest.setLoginName(companyChannel.getChannelCompany());
        cmbRequest.setBizRequests(bizRequests);

        String response = CMBUtil.doPost(companyChannel.getPrivateKey3(),
            cmbRequest);

        // 结果解析
        String RETCOD = Jsoup.parse(response).select("RETCOD").html();
        String REQSTS = Jsoup.parse(response).select("REQSTS").html();
        String RTNFLG = Jsoup.parse(response).select("RTNFLG").html();
        String YURREF = Jsoup.parse(response).select("YURREF").html();
        logger.info("银企直联直接支付返回结果： RETCOD=" + RETCOD + " REQSTS=" + REQSTS
                + " RTNFLG=" + RTNFLG + " YURREF=" + YURREF);
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
    }
}
