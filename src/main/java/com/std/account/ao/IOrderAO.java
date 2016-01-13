/**
 * @Title IOrderAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-5-6 下午3:13:29 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.annotation.ServiceModule;

/** 
 * @author: miyb 
 * @since: 2015-5-6 下午3:13:29 
 * @history:
 */
@ServiceModule
public interface IOrderAO {

    /** 
     * 审批
     * @param orderType
     * @param orderNo
     * @param approveUser
     * @param approveResult
     * @param remark 
     * @create: 2015-5-6 下午3:15:07 miyb
     * @history: 
     */
    void doApprove(String orderType, String orderNo, String approveUser,
            String approveResult, String remark);

    /** 
     * 支付
     * @param orderType
     * @param orderNo
     * @param payUser
     * @param payResult
     * @param remark
     * @param payNo
     * @param payFee
     * @param workDate 
     * @create: 2015-5-6 下午4:00:15 miyb
     * @history: 
     */
    void doPay(String orderType, String orderNo, String payUser,
            String payResult, String remark, String payNo, Long payFee,
            String workDate);

}
