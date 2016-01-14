/**
 * @Title CQOrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:33:57 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.ICQOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.ICQOrderBO;
import com.std.account.bo.ISmsOutBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.base.Paginable;
import com.std.account.common.DateUtil;
import com.std.account.common.PhoneUtil;
import com.std.account.domain.Account;
import com.std.account.domain.CQOrder;
import com.std.account.domain.User;
import com.std.account.enums.EBizType;
import com.std.account.enums.EChannel;
import com.std.account.enums.EDirection;
import com.std.account.enums.ESmsBizType;
import com.std.account.util.CalculationUtil;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午9:33:57 
 * @history:
 */
@Service
public class CQOrderAOImpl implements ICQOrderAO {
    @Autowired
    IAccountBO accountBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    ICQOrderBO cqOrderBO;

    @Autowired
    ISmsOutBO smsOutBO;

    @Override
    @Transactional
    public String doChargeOffline(String accountNumber, Long amount,
            String bankCode, String bankcardNo) {
        Account account = accountBO.getAccount(accountNumber);
        String orderNo = cqOrderBO.saveCQOrder(accountNumber,
            EDirection.PLUS.getCode(), amount, bankCode, null, bankcardNo,
            EChannel.OFFLINE.getCode());
        // 发送短信
        User user = userBO.getUser(account.getUserId());
        String mobile = user.getMobile();
        smsOutBO
            .sendSmsOut(
                mobile,
                "尊敬的"
                        + PhoneUtil.hideMobile(mobile)
                        + "用户,您于"
                        + DateUtil.dateToStr(new Date(),
                            DateUtil.DATA_TIME_PATTERN_1) + "提交的"
                        + CalculationUtil.divi(amount)
                        + "充值申请，现已进入审核阶段，请留意相关短信通知。", ESmsBizType.Charge
                    .getCode(), ESmsBizType.Charge.getValue());
        return orderNo;
    }

    @Override
    @Transactional
    public String doWithdrawOffline(String accountNumber, Long amount,
            String bankCode, String subbranch, String bankcardNo,
            String tradePwd) {
        Account account = accountBO.getAccount(accountNumber);
        // 验证交易密码
        userBO.checkTradePwd(account.getUserId(), tradePwd);
        String orderNo = cqOrderBO.saveCQOrder(accountNumber,
            EDirection.MINUS.getCode(), amount, bankCode, subbranch,
            bankcardNo, EChannel.OFFLINE.getCode());
        accountBO.freezeAmount(accountNumber, amount, EBizType.AJ_QX, orderNo);
        // 发送短信
        User user = userBO.getUser(account.getUserId());
        String mobile = user.getMobile();
        smsOutBO
            .sendSmsOut(
                mobile,
                "尊敬的"
                        + PhoneUtil.hideMobile(mobile)
                        + "用户,您于"
                        + DateUtil.dateToStr(new Date(),
                            DateUtil.DATA_TIME_PATTERN_1) + "提交的"
                        + CalculationUtil.divi(amount)
                        + "取现申请，现已进入审核阶段，请留意相关短信通知。", ESmsBizType.Withdraw
                    .getCode(), ESmsBizType.Withdraw.getValue());
        return orderNo;
    }

    /** 
     * @see com.ibis.account.ao.ICQOrderAO#queryCQOrderPage(int, int, com.ibis.account.domain.CQOrder)
     */
    @Override
    public Paginable<CQOrder> queryCQOrderPage(int start, int limit,
            CQOrder condition) {
        return cqOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<CQOrder> queryCQOrderList(CQOrder condition) {
        return cqOrderBO.queryCQOrderList(condition);
    }

    @Override
    @Transactional
    public Map<String, String> doChargeYeepay(String p1MerId,
            String accountNumber, Long amount, Long fee, String bankCode) {

        return null;
    }

    @Override
    @Transactional
    public boolean doCallbackChargeYeepay(String p1MerId, String r0_Cmd,
            String r1_Code, String r2_TrxId, String r3_Amt, String r4_Cur,
            String r5_Pid, String r6_Order, String r7_Uid, String r8_MP,
            String r9_BType, String hmac) {

        return false;
    }

    @Override
    @Transactional
    public String doInstantPay(String accountNumber, Long amount, Long fee,
            String userIp, String idNo, String userId) {

        return null;
    }

    @Override
    public boolean doCallbackInstantPay(String data, String encryptkey) {
        return false;
    }
}
