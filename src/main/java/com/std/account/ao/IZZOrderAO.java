/**
 * @Title IZZOrderAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午7:24:51 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.List;

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
    String DEFAULT_ORDER_COLUMN = "zz_no";

    /** 
     * 与未对接系统的账户进行资金划转，融资账户的钱相应变动
     * @param userId
     * @param amount（精确到厘:正数为转入；负数为转出）
     * @param oppositeSystem
     * @param oppositeAccount
     * @param remark
     * @return 
     * @create: 2015-5-8 上午9:39:10 miyb
     * @history: 
     */
    public String doTransfer(String userId, Long amount, String oppositeSystem,
            String oppositeAccount, String remark);

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
     * 统计信息：SELECT t.opposite_system,sum(amount) FROM xn_account.tli_zzorder t where t.account_number='2015102960305217' group by t.opposite_system;
     * @param accountNumber
     * @return 
     * @create: 2015年10月27日 上午11:36:10 myb858
     * @history:
     */
    public List<ZZOrder> doStatisticsDvalue(String accountNumber);

}
