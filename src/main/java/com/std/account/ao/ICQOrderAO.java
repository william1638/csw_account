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
import java.util.Map;

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
     * 线下取现（第一步：用户说“我要取钱”）
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
            String bankCode, String subbranch, String bankcardNo,
            String tradePwd);

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

    /**
     * 通过易宝进行充值
     * @param p1MerId 商家标识
     * @param accountNumber
     * @param amount
     * @param fee
     * @param bankCode
     * @return 
     * @create: 2015年10月22日 下午2:11:07 myb858
     * @history:
     */
    public Map<String, String> doChargeYeepay(String p1MerId,
            String accountNumber, Long amount, Long fee, String bankCode);

    /**
     * 处理易宝的回调
     * @param p1MerId  商家标识
     * @param r0_Cmd
     * @param r1_Code
     * @param r2_TrxId
     * @param r3_Amt
     * @param r4_Cur
     * @param r5_Pid
     * @param r6_Order
     * @param r7_Uid
     * @param r8_MP
     * @param r9_BType
     * @param hmac
     * @return 
     * @create: 2015年10月22日 下午3:32:11 myb858
     * @history:
     */
    public boolean doCallbackChargeYeepay(String p1MerId, String r0_Cmd,
            String r1_Code, String r2_TrxId, String r3_Amt, String r4_Cur,
            String r5_Pid, String r6_Order, String r7_Uid, String r8_MP,
            String r9_BType, String hmac);

    /**
     * 通过易宝一键支付进行充值
     * @param accountNumber
     * @param amount
     * @param fee
     * @param userIp
     * @param idNo
     * @param userId
     * @return 
     * @create: 2015年11月22日 下午1:39:34 haiqingzheng
     * @history:
     */
    public String doInstantPay(String accountNumber, Long amount, Long fee,
            String userIp, String idNo, String userId);

    /**
     * 一键支付回调
     * @param data
     * @param encryptkey
     * @return 
     * @create: 2015年11月22日 下午1:39:42 haiqingzheng
     * @history:
     */
    public boolean doCallbackInstantPay(String data, String encryptkey);
}
