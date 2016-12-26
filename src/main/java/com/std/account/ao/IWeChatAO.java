/**
 * @Title IWeChatAO.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:23:39 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.Map;

import com.std.account.domain.CompanyChannel;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:23:39 
 * @history:
 */
public interface IWeChatAO {
    /**
     * （微信APP支付）统一下单，下单成功返回 prepay_id
     * @param systemCode
     * @param companyCode
     * @param accountNumber
     * @param bizType
     * @param bizNote
     * @param body
     * @param totalFee
     * @param spbillCreateIp
     * @return 
     * @create: 2016年12月26日 下午8:52:44 haiqingzheng
     * @history:
     */
    public String getPrepayIdApp(String systemCode, String companyCode,
            String accountNumber, String bizType, String bizNote, String body,
            Long totalFee, String spbillCreateIp);

    /**
     * （微信公众号支付）统一下单，下单成功返回 prepay_id
     * @param systemCode
     * @param companyCode
     * @param openId
     * @param accountNumber
     * @param bizType
     * @param bizNote
     * @param body
     * @param totalFee
     * @param spbillCreateIp
     * @return 
     * @create: 2016年12月26日 下午5:47:24 haiqingzheng
     * @history:
     */
    public String getPrepayIdH5(String systemCode, String companyCode,
            String openId, String accountNumber, String bizType,
            String bizNote, String body, Long totalFee, String spbillCreateIp);

    /**
     * （微信公众号支付）支付回调
     * @param orderNo
     * @param callbackResult
     * @return 
     * @create: 2016年12月26日 下午5:47:58 haiqingzheng
     * @history:
     */
    public int doCallbackH5(String orderNo, String callbackResult);

    /**
     * 获取支付渠道配置信息
     * @param companyCode
     * @param systemCode
     * @return 
     * @create: 2016年12月26日 下午5:48:11 haiqingzheng
     * @history:
     */
    public CompanyChannel getCompanyChannel(String companyCode,
            String systemCode, String channelType);

    /**
     * 获取公众号access token
     * @param appId
     * @param appSecret
     * @return 
     * @create: 2016年12月26日 下午5:48:24 haiqingzheng
     * @history:
     */
    public String getAccessToken(String appId, String appSecret);

    /**
     * 请求订单查询接口
     * @param map
     * @return
     */
    public boolean reqOrderquery(Map<String, String> map, String channelType);
}
