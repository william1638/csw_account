/**
 * @Title IWeChatAO.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:23:39 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.domain.CallbackResult;
import com.std.account.domain.CompanyChannel;
import com.std.account.dto.res.XN802180Res;
import com.std.account.dto.res.XN802182Res;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:23:39 
 * @history:
 */
public interface IWeChatAO {
    /**
     * （微信APP支付）统一下单，下单成功返回 prepay_id
     * @param systemCode 系统编号
     * @param companyCode 公司编号
     * @param userId 用户编号
     * @param bizType 业务类型
     * @param bizNote 业务说明(body)
     * @param transAmount 发生金额
     * @param currency 币种
     * @param payGroup 支付组号
     * @return 
     * @create: 2017年2月27日 上午9:52:12 xieyj
     * @history:
     */
    public XN802180Res getPrepayIdApp(String systemCode, String companyCode,
            String userId, String bizType, String bizNote, Long transAmount,
            String currency, String payGroup);

    /**
     * （微信公众号支付）统一下单，下单成功返回 prepay_id
     * @param systemCode
     * @param companyCode
     * @param fromUserId
     * @param toUserId
     * @param transAmount
     * @param currency
     * @param payGroup
     * @param bizType
     * @param bizNote
     * @return 
     * @create: 2017年3月2日 下午3:49:59 haiqingzheng
     * @history:
     */
    public XN802182Res getPrepayIdH5(String systemCode, String companyCode,
            String fromUserId, String toUserId, Long transAmount,
            String currency, String payGroup, String bizType, String bizNote);

    // public XN802182Res generatePayParam(String prepayId);

    /**
     * （微信APP支付）支付回调
     * @param result
     * @return 
     * @create: 2016年12月26日 下午5:47:58 haiqingzheng
     * @history:
     */
    public CallbackResult doCallbackAPP(String result);

    /**
     * （微信公众号支付）支付回调
     * @param result
     * @return 
     * @create: 2016年12月26日 下午5:47:58 haiqingzheng
     * @history:
     */
    public CallbackResult doCallbackH5(String result);

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
     * 回调业务biz，通知支付结果
     * @param callbackResult 
     * @create: 2017年2月27日 下午12:40:52 haiqingzheng
     * @history:
     */
    public void doBizCallback(CallbackResult callbackResult);

}
