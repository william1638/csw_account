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
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Jour;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:05:07 
 * @history:
 */
@ServiceModule
public interface IJourAO {
    String DEFAULT_ORDER_COLUMN = "code";

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
    public String doChangeAmount(String accountNumber, String bankcardNumber,
            Long transAmount, String bizType, String bizNote,
            List<String> channelTypeList, String systemCode, String tradePwd);

    /**
     * 外部批量支付
     * @param accountNumberList
     * @param bankcardNumber
     * @param transAmount
     * @param bizType
     * @param bizNote
     * @param channelTypeList
     * @param systemCode
     * @return 
     * @create: 2017年1月4日 下午4:49:29 xieyj
     * @history:
     */
    public void doChangeAmountList(List<String> accountNumberList,
            String bankcardNumber, Long transAmount, String bizType,
            String bizNote, List<String> channelTypeList, String systemCode);

    /**
     * 回调支付
     * @param code
     * @param rollbackResult
     * @param rollbackUser
     * @param rollbackNote
     * @param systemCode 
     * @create: 2016年12月24日 上午8:21:37 xieyj
     * @history:
     */
    public void doCallBackChange(String code, String rollbackResult,
            String rollbackUser, String rollbackNote, String systemCode);

    /**
     * 批量回调支付
     * @param codeList
     * @param rollbackResult
     * @param rollbackUser
     * @param rollbackNote
     * @param systemCode 
     * @create: 2016年12月24日 上午8:21:37 xieyj
     * @history:
     */
    public void doCallBackChangeList(List<String> codeList,
            String rollbackResult, String rollbackUser, String rollbackNote,
            String systemCode);

    /**
     * 人工调账
     * @param code
     * @param checkAmount
     * @param checkUser
     * @param checkNote
     * @param systemCode 
     * @create: 2016年12月25日 下午3:58:53 xieyj
     * @history:
     */
    public void checkJour(String code, Long checkAmount, String checkUser,
            String checkNote, String systemCode);

    /**
     * 人工调账
     * @param code
     * @param adjustResult 1 通过，0 不通过
     * @param adjustUser
     * @param adjustNote 
     * @param systemCode 
     * @create: 2016年12月25日 下午5:23:05 xieyj
     * @history:
     */
    public void adjustJour(String code, String adjustResult, String adjustUser,
            String adjustNote, String systemCode);

    /**
     * 兑换申请
     * @param systemCode
     * @param userId
     * @param transAmount
     * @param bizType 
     * @create: 2017年1月5日 下午1:00:52 xieyj
     * @history:
     */
    public String applyExchangeAmount(String systemCode, String userId,
            Long transAmount, String bizType);

    /**
     * 兑换审批
     * @param systemCode
     * @param code
     * @param rate
     * @param approveResult
     * @param approver
     * @param approveNote 
     * @create: 2017年1月5日 下午1:10:48 xieyj
     * @history:
     */
    public void approveExchangeAmount(String systemCode, String code,
            Double rate, String approveResult, String approver,
            String approveNote);

    /**
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2016年12月24日 上午7:55:52 xieyj
     * @history:
     */
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition);

    /**
     * @param condition
     * @return 
     * @create: 2016年12月24日 上午7:55:59 xieyj
     * @history:
     */
    public List<Jour> queryJourList(Jour condition);

    /**
     * @param code
     * @param systemCode
     * @return 
     * @create: 2016年12月24日 上午8:20:47 xieyj
     * @history:
     */
    public Jour getJour(String code, String systemCode);

    /**
     * 统计用户某种业务类型下某账户发生的金额
     * @param systemCode 系统编号
     * @param userId 用户编号
     * @param currency 币种
     * @param bizType 业务类型
     * @return 
     * @create: 2017年2月23日 下午4:09:25 xieyj
     * @history:
     */
    public Long getStatisticsTransAmount(String systemCode, String userId,
            String currency, String bizType);
}
