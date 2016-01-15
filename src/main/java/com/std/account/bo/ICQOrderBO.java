/**
 * @Title ICQOrderBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:19:56 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.CQOrder;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:19:56 
 * @history:
 */
public interface ICQOrderBO extends IPaginableBO<CQOrder> {
    /**
     * 线下充取专用
     * @param accountNumber
     * @param direction
     * @param amount
     * @param bankCode
     * @param subbranch
     * @param bankcardNo
     * @param channel
     * @return 
     * @create: 2015年12月10日 下午3:02:54 myb858
     * @history:
     */
    public String saveCQOrder(String accountNumber, String direction,
            Long amount, String bankCode, String subbranch, String bankcardNo,
            String channel);

    /**
     * 
     * @param cqNo
     * @param approveUser
     * @param approveResult
     * @param remark
     * @return 
     * @create: 2015-5-5 下午1:57:12 miyb
     * @history:
     */
    public int refreshApproveOrder(String cqNo, String approveUser,
            String approveResult, String remark);

    /** 
     * @param cqNo
     * @param payUser
     * @param payResult
     * @param remark
     * @param payNo
     * @param payFee
     * @param workDate 
     * @create: 2015-5-6 下午4:26:31 miyb
     * @history: 
     */
    public int refreshPayOrder(String cqNo, String payUser, String payResult,
            String remark, String payNo, Long payFee, String workDate);

    /**
     * 获取单条订单
     * @param cqNo
     * @return 
     * @create: 2015-5-5 上午11:29:22 miyb
     * @history:
     */
    public CQOrder getCQOrder(String cqNo);

    /**
     * 获取订单列表
     * @param condition
     * @return 
     * @create: 2015-5-5 上午11:29:47 miyb
     * @history:
     */
    public List<CQOrder> queryCQOrderList(CQOrder condition);

}
