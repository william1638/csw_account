/**
 * @Title IAlipayAO.java 
 * @Package com.std.account.ao 
 * @Description 
 * @author haiqingzheng  
 * @date 2017年1月11日 下午8:55:34 
 * @version V1.0   
 */
package com.std.account.ao;

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
     * @param systemCode
     * @param companyCode
     * @param result
     * @return 
     * @create: 2017年1月12日 下午4:01:16 haiqingzheng
     * @history:
     */
    public Object doCallbackAPP(String systemCode, String companyCode,
            String result);
}
