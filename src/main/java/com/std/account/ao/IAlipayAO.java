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
     * @param fromUserId
     * @param toUserId
     * @param bizType
     * @param fromBizNote
     * @param toBizNote
     * @param transAmount
     * @param payGroup
     * @return 
     * @create: 2017年3月29日 上午10:01:59 haiqingzheng
     * @history:
     */
    public Object getSignedOrder(String fromUserId, String toUserId,
            String bizType, String fromBizNote, String toBizNote,
            Long transAmount, String payGroup);

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
