/**
 * @Title IXNBOrderBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:27:36 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.XNBOrder;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:27:36 
 * @history:
 */
public interface IXNBOrderBO extends IPaginableBO<XNBOrder> {

    /** 
     * @param accountNumber
     * @param status
     * @param type
     * @param score
     * @param amount
     * @param remark
     * @return 
     * @create: 2015-5-8 上午9:12:19 miyb
     * @history: 
     */
    public String saveXNBOrder(String accountNumber, String status,
            String type, Long score, Long amount, String remark);

    /**
     * 
     * @param xnbNo
     * @param approveUser
     * @param approveResult
     * @param remark
     * @return 
     * @create: 2015-5-5 下午1:24:43 miyb
     * @history:
     */
    public int refreshApproveOrder(String xnbNo, String approveUser,
            String approveResult, String remark);

    /**
     *  获取单条订单
     * @param xnbNo
     * @return 
     * @create: 2015-5-5 下午1:06:46 miyb
     * @history:
     */
    public XNBOrder getXNBOrder(String xnbNo);

    /**
     * 获取订单列表
     * @param condition
     * @return 
     * @create: 2015-5-5 下午1:06:53 miyb
     * @history:
     */
    public List<XNBOrder> queryXNBOrderList(XNBOrder condition);

}
