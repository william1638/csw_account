/**
 * @Title ZZOrderAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午9:35:01 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IZZOrderAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IZZOrderBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.domain.ZZOrder;
import com.std.account.enums.EBizType;
import com.std.account.enums.ECurrency;
import com.std.account.enums.EDirection;
import com.std.account.enums.EUser;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午9:35:01 
 * @history:
 */
@Service
public class ZZOrderAOImpl implements IZZOrderAO {
    @Autowired
    IUserBO userBO;

    @Autowired
    IAccountBO accountBO;

    @Autowired
    IZZOrderBO zzOrderBO;

    @Override
    public Paginable<ZZOrder> queryZZOrderPage(int start, int limit,
            ZZOrder condition) {
        return zzOrderBO.getPaginable(start, limit, condition);
    }

    @Override
    @Transactional
    public String doTransferOSS(String accountNumber, String direction,
            Long amount, Long fee, String remark) {
        Account account = accountBO.getAccount(accountNumber);
        if (account == null) {
            throw new BizException("li01004", accountNumber + "账户不存在");
        }
        return doTransfer(accountNumber, direction, amount, fee, remark);
    }

    @Override
    @Transactional
    public String doTransferFRONT(String accountNumber, String direction,
            Long amount, Long fee, String remark, String tradePwd) {
        Account account = accountBO.getAccount(accountNumber);
        if (account == null) {
            throw new BizException("li01004", accountNumber + "账户不存在");
        }
        // 校验交易密码
        userBO.checkTradePwd(account.getUserId(), tradePwd);
        return doTransfer(accountNumber, direction, amount, fee, remark);
    }

    private String doTransfer(String accountNumber, String direction,
            Long amount, Long fee, String remark) {
        EBizType bizType = null;
        Long transAmount = null;
        EDirection dir = null;
        if (EDirection.PLUS.getCode().equalsIgnoreCase(direction)) {
            bizType = EBizType.AJ_MH;
            transAmount = amount;
            dir = EDirection.PLUS;
        }
        if (EDirection.MINUS.getCode().equalsIgnoreCase(direction)) {
            bizType = EBizType.AJ_XF;
            transAmount = -amount;
            dir = EDirection.MINUS;
        }
        String zzNo = zzOrderBO.saveZZOrder(accountNumber, dir, amount, fee,
            remark);
        // 资金变动
        accountBO.refreshAmount(accountNumber, transAmount, zzNo, bizType);
        return zzNo;
    }

    @Override
    @Transactional
    public void doBuyTransfer(String fromUserId, String toUserId,
            String direction, Long amount, Long cnyAmount, Long fee,
            String remark) {
        // 虚拟币划账
        Account fromXnbAccount = accountBO.getAccountByUser(fromUserId,
            ECurrency.XNB.getCode());
        Account toXnbAccount = accountBO.getAccountByUser(toUserId,
            ECurrency.XNB.getCode());
        // 人民币划账
        Account fromCnyAccount = accountBO.getAccountByUser(fromUserId,
            ECurrency.CNY.getCode());
        Account toCnyAccount = accountBO.getAccountByUser(toUserId,
            ECurrency.CNY.getCode());

        EBizType bizType1 = null;
        EBizType bizType2 = null;
        if (EDirection.PLUS.getCode().equalsIgnoreCase(direction)) {
            bizType1 = EBizType.AJ_XF;
            bizType2 = EBizType.AJ_MH;
        } else if (EDirection.MINUS.getCode().equalsIgnoreCase(direction)) {
            bizType1 = EBizType.AJ_TKFX;
            bizType2 = EBizType.AJ_TKKK;
        }
        this.doHz(fromXnbAccount.getAccountNumber(),
            toXnbAccount.getAccountNumber(), direction, amount, fee, bizType1,
            bizType2, remark);
        this.doHz(fromCnyAccount.getAccountNumber(),
            toCnyAccount.getAccountNumber(), direction, cnyAmount, fee,
            bizType1, bizType2, remark);
    }

    @Override
    @Transactional
    public String doHZOss(String fromAccountNumber, String accountNumber,
            String direction, Long amount, Long fee, String remark) {
        return this.doHz(fromAccountNumber, accountNumber, direction, amount,
            fee, EBizType.AJ_HDKJF, EBizType.AJ_HDJJF, remark);
    }

    // 商户消费
    // 1、扣除消费积分，人民币，商户增加消费积分，人民币
    // 2、用户增加返现积分，人民币
    // 3、顶级商家扣除返现积分，人民币
    @Override
    public void doShopMerchant(String fromUserId, String toUserId, Long amount,
            Long cnyAmount, Long jfCashBack, Long cnyCashBack) {
        // 虚拟币划账
        Account fromXnbAccount = accountBO.getAccountByUser(fromUserId,
            ECurrency.XNB.getCode());
        Account toXnbAccount = accountBO.getAccountByUser(toUserId,
            ECurrency.XNB.getCode());
        if (amount > 0) {
            this.doHz(fromXnbAccount.getAccountNumber(),
                toXnbAccount.getAccountNumber(), EDirection.PLUS.getCode(),
                amount, new Long(0), EBizType.AJ_MDXKJF, EBizType.AJ_MDXJJF,
                "线下商铺抵现消费");
        }

        // 人民币账户
        Account fromCnyAccount = accountBO.getAccountByUser(fromUserId,
            ECurrency.CNY.getCode());
        // 顶级积分商
        Account topCnyAccount = accountBO.getAccountByUser(
            EUser.Top_Integral.getCode(), ECurrency.CNY.getCode());
        if (cnyCashBack > 0) {
            this.doHz(topCnyAccount.getAccountNumber(),
                fromCnyAccount.getAccountNumber(), EDirection.PLUS.getCode(),
                cnyCashBack, new Long(0), EBizType.AJ_FXKQ, EBizType.AJ_FXJQ,
                "返现");
        }
    }

    // 购买积分
    // 1、用户增加积分，扣除人民币
    // 2、商家增加人民币，扣除积分
    @Override
    public void doBuyJf(String fromUserId, Long cnyAmount, Long amount) {
        String toUserId = EUser.Top_Integral.getCode();
        // 虚拟币划账
        Account fromXnbAccount = accountBO.getAccountByUser(fromUserId,
            ECurrency.XNB.getCode());
        Account toXnbAccount = accountBO.getAccountByUser(toUserId,
            ECurrency.XNB.getCode());
        // 人民币划账
        Account fromCnyAccount = accountBO.getAccountByUser(fromUserId,
            ECurrency.CNY.getCode());
        Account toCnyAccount = accountBO.getAccountByUser(toUserId,
            ECurrency.CNY.getCode());
        this.doHz(fromCnyAccount.getAccountNumber(),
            toCnyAccount.getAccountNumber(), EDirection.PLUS.getCode(),
            cnyAmount, new Long(0), EBizType.AJ_GMJF, EBizType.AJ_DHSY,
            "用户用现金向积分商购买积分");
        this.doHz(toXnbAccount.getAccountNumber(),
            fromXnbAccount.getAccountNumber(), EDirection.PLUS.getCode(),
            amount, new Long(0), EBizType.AJ_GMKJF, EBizType.AJ_GMJJF,
            "积分商向用户划转积分");
    }

    private String doHz(String fromAccountNumber, String accountNumber,
            String direction, Long amount, Long fee, EBizType bizType1,
            EBizType bizType2, String remark) {
        String zzNo = null;
        if (amount != null && amount != 0L) {
            EBizType fromBizType = null;
            EBizType bizType = null;
            Long transAmount = null;
            EDirection dir = null;
            if (EDirection.PLUS.getCode().equalsIgnoreCase(direction)) {
                fromBizType = bizType1;
                bizType = bizType2;
                transAmount = amount;
                dir = EDirection.PLUS;
            }
            if (EDirection.MINUS.getCode().equalsIgnoreCase(direction)) {
                fromBizType = bizType2;
                bizType = bizType1;
                transAmount = -amount;
                dir = EDirection.MINUS;
            }
            zzNo = zzOrderBO.saveHZOrder(fromAccountNumber, accountNumber, dir,
                amount, fee, remark);
            // 资金变动
            accountBO.refreshAmount(fromAccountNumber, -transAmount, zzNo,
                fromBizType);
            // 资金变动
            accountBO.refreshAmount(accountNumber, transAmount, zzNo, bizType);
        }
        return zzNo;
    }
}
