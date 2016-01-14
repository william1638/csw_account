/**
 * @Title IYTOrderBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-5-9 下午2:49:04 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.YTOrder;

/** 
 * @author: miyb 
 * @since: 2015-5-9 下午2:49:04 
 * @history:
 */
public interface IYTOrderBO extends IPaginableBO<YTOrder> {
    /**
     * 
     * @param accountNumber
     * @param status
     * @param bizType
     * @param amount
     * @param remark
     * @return 
     * @create: 2015-5-9 下午3:19:02 miyb
     * @history:
     */
    public String saveYTOrder(String accountNumber, String status,
            String bizType, Long amount, String remark);

    /**
     * 
     * @param ytNo
     * @param approveUser
     * @param approveResult
     * @param remark
     * @return 
     * @create: 2015-5-5 下午1:41:25 miyb
     * @history:
     */
    public int refreshApproveOrder(String ytNo, String approveUser,
            String approveResult, String remark);

    /**
     * 获取单条订单
     * @param ytNo
     * @return 
     * @create: 2015-5-5 下午1:03:42 miyb
     * @history:
     */
    public YTOrder getYTOrder(String ytNo);

    /**
     * 获取订单列表
     * @param condition
     * @return 
     * @create: 2015-5-5 下午1:04:05 miyb
     * @history:
     */
    public List<YTOrder> queryYTOrderList(YTOrder condition);

}
