/**
 * @Title IHLOrderBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:20:06 
 * @version V1.0   
 */
package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.HLOrder;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EDirection;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:20:06 
 * @history:
 */
public interface IHLOrderBO extends IPaginableBO<HLOrder> {

    public String saveHLOrder(String accountNumber, EDirection direction,
            Long amount, String applyUser, String applyNote);

    public void refreshApproveOrder(String hlNo, String approveUser,
            EBoolean approveResult, String approveNote);

    public HLOrder getHLOrder(String code);
}
