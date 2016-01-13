/**
 * @Title IHLOrderBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:20:06 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.HLOrder;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:20:06 
 * @history:
 */
public interface IHLOrderBO extends IPaginableBO<HLOrder> {
    /**
     * 
     * @param accountNumber
     * @param amount
     * @param applyUser
     * @param applyNote
     * @return 
     * @create: 2015-5-5 下午1:41:37 miyb
     * @history:
     */
    public String saveHLOrder(String accountNumber, Long amount,
            String applyUser, String applyNote);

    /**
     * 
     * @param hlNo
     * @param approveUser
     * @param approveResult
     * @param remark
     * @return 
     * @create: 2015-5-5 下午1:41:25 miyb
     * @history:
     */
    public int refreshApproveOrder(String hlNo, String approveUser,
            String approveResult, String remark);

    /**
     * 获取单条订单
     * @param hlNo
     * @return 
     * @create: 2015-5-5 下午1:03:42 miyb
     * @history:
     */
    public HLOrder getHLOrder(String hlNo);

    /**
     * 获取订单列表
     * @param condition
     * @return 
     * @create: 2015-5-5 下午1:04:05 miyb
     * @history:
     */
    public List<HLOrder> queryHLOrderList(HLOrder condition);
}
