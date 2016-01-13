/**
 * @Title IXNBOrderAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午7:24:10 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.XNBOrder;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午7:24:10 
 * @history:
 */
@ServiceModule
public interface IXNBOrderAO {
    String DEFAULT_ORDER_COLUMN = "xnb_no";

    /** 
     * @param isNeedApprove:非优质虚拟币兑换，需要人工审批，审批通过后才能转换成融资账户可用余额
     * @param accountNumber
     * @param type 积分类型（0打头不用审批;1打头需要审批）
     * @param score
     * @param amount
     * @param remark
     * @return 
     * @create: 2015-5-8 上午9:03:59 miyb
     * @history: 
     */
    public String doExchange(boolean isNeedApprove, String accountNumber,
            String type, Long score, Long amount, String remark);

    /** 
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-5-7 下午5:27:30 miyb
     * @history: 
     */
    public Paginable<XNBOrder> queryXNBOrderPage(int start, int limit,
            XNBOrder condition);

}
