/**
 * @Title IZZOrderBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:20:30 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.ZZOrder;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:20:30 
 * @history:
 */
public interface IZZOrderBO extends IPaginableBO<ZZOrder> {

    /** 
     * @param accountNumber
     * @param direction
     * @param amount
     * @param oppositeSystem
     * @param oppositeAccount
     * @param remark
     * @return 
     * @create: 2015-5-8 上午9:49:58 miyb
     * @history: 
     */
    public String saveZZOrder(String accountNumber, String direction,
            Long amount, String oppositeSystem, String oppositeAccount,
            String remark);

    /**
     * 获取单条订单
     * @param zzNo
     * @return 
     * @create: 2015-5-5 下午1:06:25 miyb
     * @history:
     */
    public ZZOrder getZZOrder(String zzNo);

    /**
     * 获取订单列表
     * @param condition
     * @return 
     * @create: 2015-5-5 下午1:06:33 miyb
     * @history:
     */
    public List<ZZOrder> queryZZOrderList(ZZOrder condition);

    /**
     * 统计信息
     * @param accountNumber
     * @return 
     * @create: 2015年10月27日 上午11:38:16 myb858
     * @history:
     */
    public List<ZZOrder> doStatisticsDvalue(String accountNumber);

}
