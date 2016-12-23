package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Jour;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;

/**
 * @author: xieyj 
 * @since: 2016年12月23日 下午2:40:13 
 * @history:
 */
public interface IJourBO extends IPaginableBO<Jour> {

    /**
     * 获取详情
     * @param code
     * @return 
     * @create: 2016年11月10日 下午6:10:50 xieyj
     * @history:
     */
    public Jour getAccountJour(String code);

    /**
     * 新增未变动金额之流水
     * @param systemCode
     * @param accountNumber
     * @param channelType
     * @param payType
     * @param bizType
     * @param bizNote
     * @param preAmount
     * @param transAmount
     * @return 
     * @create: 2016年12月23日 下午2:52:19 xieyj
     * @history:
     */
    public String addToChangeJour(String systemCode, String accountNumber,
            EChannelType channelType, EBizType bizType, String bizNote,
            Long preAmount, Long transAmount);

    /**
     * 回调处理流水
     * @param code
     * @param rollbackUser
     * @param rollbackNote
     * @return 
     * @create: 2016年12月23日 下午2:52:44 xieyj
     * @history:
     */
    public int callBackChangeJour(String code, String rollbackUser,
            String rollbackNote);

    /**
     * 新增已变动金额之流水
     * @param systemCode
     * @param accountNumber
     * @param channelType
     * @param channelOrder
     * @param payType
     * @param bizType
     * @param bizNote
     * @param preAmount
     * @param transAmount
     * @return 
     * @create: 2016年12月23日 下午2:48:40 xieyj
     * @history:
     */
    public String addChangedJour(String systemCode, String accountNumber,
            EChannelType channelType, String channelOrder, String bizType,
            String bizNote, Long preAmount, Long transAmount);

    /**
     * 对账结果录入
     * @param code
     * @param checkResult
     * @param checkUser 
     * @create: 2016年12月23日 下午2:50:20 xieyj
     * @history:
     */
    public void doCheckAccount(String code, EBoolean checkResult,
            String checkUser);

    /**
     * 调账结果录入
     * @param code
     * @param adjustResult
     * @param adjustUser 
     * @create: 2016年12月23日 下午2:50:13 xieyj
     * @history:
     */
    public void doAdjustAccount(String code, EBoolean adjustResult,
            String adjustUser);

}
