/**
 * @Title IWithdrawBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:19:56 
 * @version V1.0   
 */
package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Withdraw;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EToType;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:19:56 
 * @history:
 */
public interface IWithdrawBO extends IPaginableBO<Withdraw> {

    public String saveWithdrawOffline(String accountNumber, Long amount,
            EToType toType, String toCode);

    public void refreshApproveOrder(String code, String approveUser,
            EBoolean approveResult, String approveNote);

    public void refreshPayOrder(String code, String payUser,
            EBoolean payResult, String payNote, String refNo, Long fee);

    public Withdraw getWithdraw(String code);

}
