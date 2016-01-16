/**
  * @Title ICQOrderAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午7:22:58 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.List;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.CQOrder;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午7:22:58 
 * @history:
 */
@ServiceModule
public interface ICQOrderAO {
    String DEFAULT_ORDER_COLUMN = "cq_no";

    /** 
     * 线下充值（第一步：用户说“我充了”）
     * @param accountNumber
     * @param amount
     * @param bankCode
     * @param bankcardNo
     * @return 
     * @create: 2015-5-6 下午2:32:00 miyb
     * @history: 
     */
    public String doChargeOffline(String accountNumber, Long amount,
            String bankCode, String bankcardNo);

    /**
     * 前端线下取现（第一步：用户说“我要取钱”）
     * @param accountNumber
     * @param amount
     * @param bankCode
     * @param subbranch
     * @param bankcardNo
     * @param tradePwd
     * @return 
     * @create: 2015年12月10日 下午3:01:38 myb858
     * @history:
     */
    public String doWithdrawOffline(String accountNumber, Long amount,
            String bankCode, String bankcardNo, String tradePwd,
            String subbranch);

    /**
     * 管理端线下取现（第一步：帮用户说“我要取钱”）
     * @param accountNumber
     * @param amount
     * @param bankCode
     * @param bankcardNo
     * @param subbranch
     * @return 
     * @create: 2016年1月16日 下午2:40:53 myb858
     * @history:
     */
    public String doWithdrawOSS(String accountNumber, Long amount,
            String bankCode, String bankcardNo, String subbranch);

    /** 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-5-7 下午5:22:37 miyb
     * @history: 
     */
    public Paginable<CQOrder> queryCQOrderPage(int start, int limit,
            CQOrder condition);

    /**
     * 充取记录查询
     * @param condition
     * @return 
     * @create: 2015年12月12日 下午5:10:07 haiqingzheng
     * @history:
     */
    public List<CQOrder> queryCQOrderList(CQOrder condition);

    public void doApproveCharge(String orderNo, String approveUser,
            String approveResult, String remark);

}
