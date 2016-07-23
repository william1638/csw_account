/**
  * @Title ICQOrderAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午7:22:58 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Withdraw;
import com.std.account.enums.ECurrency;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午7:22:58 
 * @history:
 */
@ServiceModule
public interface IWithdrawAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /**
     * 分页查询取现列表
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年5月26日 下午2:42:20 myb858
     * @history:
     */

    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition);

    /**
     * 管理端线下取现（第一步：帮用户说“我要取钱”）
     * @param accountNumber
     * @param amount
     * @param toType
     * @param toCode
     * @param toBelong
     * @return 
     * @create: 2016年6月15日 上午10:23:49 xieyj
     * @history:
     */
    public String doWithdrawOSS(String accountNumber, Long amount,
            String toType, String toCode, String toBelong);

    /**
     * 兑现积分
     * @param data
     * @param currency
     * @return 
     * @create: 2016年7月23日 上午8:54:34 xieyj
     * @history:
     */
    public String doWithdrawOSS(Withdraw data, ECurrency currency);

    /**
     * 前端线下取现（第一步：用户说“我要取钱”）
     * @param accountNumber
     * @param amount
     * @param toType
     * @param toCode
     * @param toBelong
     * @param tradePwd
     * @return 
     * @create: 2016年6月15日 上午10:24:29 xieyj
     * @history:
     */
    public String doWithdrawOffline(String accountNumber, Long amount,
            String toType, String toCode, String toBelong, String tradePwd);

    public void doApproveWithdraw(String withdrawNo, String approveUser,
            String approveResult, String approveNote, ECurrency currency);

    public void doPayWithdraw(String withdrawNo, String payUser,
            String payResult, String payNote, String refNo, Long fee,
            ECurrency currency);

}
