/**
 * @Title IAlipayAO.java 
 * @Package com.std.account.ao 
 * @Description 
 * @author haiqingzheng  
 * @date 2017年1月11日 下午8:55:34 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.domain.CallbackResult;

/** 
 * @author: haiqingzheng 
 * @since: 2017年1月11日 下午8:55:34 
 * @history:
 */
public interface IAlipayAO {

    /**
     * 获取签名后的订单信息
     * @param systemCode
     * @param companyCode
     * @param userId
     * @param bizType
     * @param bizNote
     * @param body
     * @param totalFee
     * @return 
     * @create: 2017年1月12日 上午11:06:04 haiqingzheng
     * @history:
     */
    public Object getSignedOrder(String systemCode, String companyCode,
            String userId, String bizType, String bizNote, String body,
            Long totalFee);

    /**
     * （支付宝APP支付）支付回调
     * @param result
     * @return 
     * @create: 2017年3月28日 下午9:02:35 haiqingzheng
     * @history:
     */
    public CallbackResult doCallbackAPP(String result);

    public void doBizCallback(CallbackResult callbackResult);
}
