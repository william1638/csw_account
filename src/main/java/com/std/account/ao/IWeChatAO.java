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
import com.std.account.dto.res.XN002500Res;
import com.std.account.dto.res.XN002501Res;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:23:39 
 * @history:
 */
public interface IWeChatAO {
    /**
     * （微信APP支付）统一下单，下单成功返回 prepay_id
     * @param fromUserId
     * @param toUserId
     * @param bizType
     * @param fromBizNote
     * @param toBizNote
     * @param transAmount
     * @param payGroup
     * @return 
     * @create: 2017年3月23日 下午7:13:15 haiqingzheng
     * @history:
     */
    public XN002500Res getPrepayIdApp(String fromUserId, String toUserId,
            String bizType, String fromBizNote, String toBizNote,
            Long transAmount, String payGroup);

    /**
     * （微信公众号支付）统一下单，下单成功返回 prepay_id
     * @param fromUserId
     * @param fromOpenId
     * @param toUserId
     * @param bizType
     * @param fromBizNote
     * @param toBizNote
     * @param transAmount
     * @param payGroup
     * @return 
     * @create: 2017年3月23日 下午7:57:51 haiqingzheng
     * @history:
     */
    public XN002501Res getPrepayIdH5(String fromUserId, String fromOpenId,
            String toUserId, String bizType, String fromBizNote,
            String toBizNote, Long transAmount, String payGroup);

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
