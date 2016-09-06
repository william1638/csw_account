/**
 * @Title IZZOrderAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午7:24:51 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.ZZOrder;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午7:24:51 
 * @history:
 */
@ServiceModule
public interface IZZOrderAO {
    String DEFAULT_ORDER_COLUMN = "code";

    /** 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-5-7 下午5:41:49 miyb
     * @history: 
     */
    public Paginable<ZZOrder> queryZZOrderPage(int start, int limit,
            ZZOrder condition);

    /** 
     * 管理端划转金额
     * @param accountNumber
     * @param direction
     * @param amount
     * @param fee
     * @param remark
     * @return 
     * @create: 2016年5月26日 下午3:57:01 myb858
     * @history:
     */
    public String doTransferOSS(String accountNumber, String direction,
            Long amount, Long fee, String remark);

    /**
     * 管理端购物划转金额(积分+人民币)
     * @param fromUserId
     * @param toUserId
     * @param direction
     * @param amount
     * @param cnyAmount
     * @param fee
     * @param remark 
     * @create: 2016年9月5日 下午11:14:58 xieyj
     * @history:
     */
    public void doBuyTransfer(String fromUserId, String toUserId,
            String direction, Long amount, Long cnyAmount, Long fee,
            String remark);

    /**
     * 前端划转金额
     * @param accountNumber
     * @param direction
     * @param amount
     * @param fee
     * @param remark
     * @param tradePwd
     * @return 
     * @create: 2016年5月26日 下午4:00:22 myb858
     * @history:
     */
    public String doTransferFRONT(String accountNumber, String direction,
            Long amount, Long fee, String remark, String tradePwd);

    /** 
     * 管理端账户间划转金额
     * @param fromAccountNumber
     * @param accountNumber
     * @param direction
     * @param amount
     * @param fee
     * @param remark
     * @return 
     * @create: 2016年5月26日 下午3:57:01 myb858
     * @history:
     */
    public String doHZOss(String fromAccountNumber, String accountNumber,
            String direction, Long amount, Long fee, String remark);

}
