/**
 * @Title IZZOrderBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:20:30 
 * @version V1.0   
 */
package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.ZZOrder;
import com.std.account.enums.EDirection;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:20:30 
 * @history:
 */
public interface IZZOrderBO extends IPaginableBO<ZZOrder> {

    public String saveZZOrder(String accountNumber, EDirection direction,
            Long amount, Long fee, String remark);

    public String saveHZOrder(String fromAccountNumber, String accountNumber,
            EDirection direction, Long amount, Long fee, String remark);

    public ZZOrder getZZOrder(String code);

}
