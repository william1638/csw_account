/**
 * @Title IWeChatAO.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:23:39 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.domain.CompanyChannel;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:23:39 
 * @history:
 */
public interface IWeChatAO {
    /**
     * 统一下单，下单成功返回 prepay_id
     * @param systemCode
     * @param body
     * @param totalFee
     * @param spbillCreateIp
     * @param notifyUrl
     * @return 
     * @create: 2016年12月23日 上午11:18:05 haiqingzheng
     * @history:
     */
    public String getPrepayIdApp(String systemCode, String body, Long totalFee,
            String spbillCreateIp, String notifyUrl);

    public String getPrepayIdH5(String systemCode, String companyCode,
            String openId, String accountNumber, String bizType,
            String bizNote, String body, Long totalFee, String spbillCreateIp);

    public int doCallbackH5(String orderNo, String callbackResult);

    public CompanyChannel getCompanyChannel(String companyCode,
            String systemCode);

    public String getAccessToken(String appId, String appSecret);
}
