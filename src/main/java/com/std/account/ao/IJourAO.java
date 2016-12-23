/**
 * @Title IJourAO.java 
 * @Package com.std.account.ao 
 * @Description 
 * @author xieyj  
 * @date 2016年12月23日 下午9:05:07 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.List;

import com.std.account.annotation.ServiceModule;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:05:07 
 * @history:
 */
@ServiceModule
public interface IJourAO {
    /**
     * 外部支付待回调
     * @param accountNumber
     * @param bankcardNumber
     * @param transAmount
     * @param bizType
     * @param bizNote
     * @param channelTypeList
     * @param systemCode 
     * @create: 2016年12月23日 下午9:09:43 xieyj
     * @history:
     */
    public void doChangeAmount(String accountNumber, String bankcardNumber,
            Long transAmount, String bizType, String bizNote,
            List<String> channelTypeList, String systemCode);

    /**
     * 回调支付
     * @param code
     * @param rollbackResult
     * @param rollbackUser
     * @param rollbackNote
     * @create: 2016年12月23日 下午10:06:56 xieyj
     * @history:
     */
    public void doCallBackChange(String code, String rollbackResult,
            String rollbackUser, String rollbackNote);

}
