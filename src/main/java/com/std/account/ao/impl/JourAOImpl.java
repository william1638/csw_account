/**
 * @Title IJourAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年12月23日 下午9:16:58 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.std.account.ao.IJourAO;
import com.std.account.bo.IAccountBO;
import com.std.account.bo.IBankcardBO;
import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.IJourBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.domain.Bankcard;
import com.std.account.domain.Jour;
import com.std.account.enums.EBizType;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EChannelType;
import com.std.account.enums.EJourStatus;
import com.std.account.enums.ESysUser;
import com.std.account.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2016年12月23日 下午9:16:58 
 * @history:
 */
@Service
public class JourAOImpl implements IJourAO {

    @Autowired
    private ICompanyChannelBO companyChannelBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IBankcardBO bankcardBO;

    /*
     * 外部账支付：1、产生支付申请订单；2、返回支付链接；
     */
    @Override
    public String doChangeAmount(String accountNumber, String bankcardNumber,
            Long transAmount, String bizType, String bizNote,
            List<String> channelTypeList, String systemCode, String tradePwd) {
        Account account = accountBO.getAccount(systemCode, accountNumber);
        if (StringUtils.isNotBlank(tradePwd)) {
            userBO.checkTradePwd(account.getUserId(), tradePwd);
        }
        String payUrl = null;
        EChannelType channelType = companyChannelBO.getBestChannel(systemCode,
            channelTypeList);
        // 业务备注前端没有传进来，由程序生成
        if (StringUtils.isNotBlank(bankcardNumber)) {
            Bankcard bankcard = bankcardBO
                .getBankcardByBankcardNumber(bankcardNumber);
            if (bankcard != null) {
                bizNote += "卡号：" + bankcardNumber + " ";
                bizNote += "银行：" + bankcard.getBankName() + " ";
                if (StringUtils.isNotBlank(bankcard.getSubbranch())) {
                    bizNote += "支行：" + bankcard.getSubbranch();
                }
            } else {
                bizNote = bankcardNumber;
            }
        } else {
            bizNote = EBizType.getBizTypeMap().get(bizType).getValue();
        }
        String code = jourBO.addToChangeJour(systemCode, accountNumber,
            channelType.getCode(), bizType, bizNote, transAmount, null);
        // 取现冻结
        if (EBizType.AJ_QX.getCode().equals(bizType)) {
            if (EChannelType.CZB.getCode().equals(channelType.getCode())) {
                accountBO.frozenAmount(systemCode, accountNumber, -transAmount,
                    code);
            }
        }
        return payUrl;
    }

    /*
     * 外部账批量支付：1、产生支付申请订单；2、返回支付链接；
     */
    @Override
    @Transactional
    public void doChangeAmountList(List<String> accountNumberList,
            String bankcardNumber, Long transAmount, String bizType,
            String bizNote, List<String> channelTypeList, String systemCode) {
        for (String accountNumber : accountNumberList) {
            this.doChangeAmount(accountNumber, bankcardNumber, transAmount,
                bizType, bizNote, channelTypeList, systemCode, null);
        }
    }

    /*
     * 回调方法： 1、审核通过扣除金额；审核不通过，资金原路返回
     */
    @Override
    @Transactional
    public void doCallBackChange(String code, String rollbackResult,
            String rollbackUser, String rollbackNote, String systemCode) {
        Jour data = jourBO.getJour(code, systemCode);
        // 判断流水状态
        if (!EJourStatus.todoCallBack.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "申请记录状态不是刚生成待回调状态，无法审批");
        }
        Account account = accountBO.getAccount(systemCode,
            data.getAccountNumber());
        Long preAmount = account.getAmount();
        Long postAmount = null;
        if (EBoolean.YES.getCode().equals(rollbackResult)) {
            if (EBizType.AJ_CZ.getCode().equals(data.getBizType())) {
                accountBO.transAmountNotJour(data.getSystemCode(),
                    data.getAccountNumber(), data.getTransAmount(), code);
                // 更新发生后金额
                postAmount = preAmount + data.getTransAmount();
            } else if (EBizType.AJ_QX.getCode().equals(data.getBizType())) {
                accountBO.unfrozenAmount(data.getSystemCode(),
                    EBoolean.YES.getCode(), data.getAccountNumber(),
                    -data.getTransAmount(), code);
                postAmount = preAmount;
                preAmount = preAmount - data.getTransAmount();
            }
        } else {
            if (EBizType.AJ_QX.getCode().equals(data.getBizType())) {
                accountBO.unfrozenAmount(data.getSystemCode(),
                    EBoolean.NO.getCode(), data.getAccountNumber(),
                    -data.getTransAmount(), code);
            }
            postAmount = preAmount - data.getTransAmount();
            preAmount = postAmount;
        }
        jourBO.callBackChangeJour(code, rollbackResult, rollbackUser,
            rollbackNote, preAmount, postAmount);
    }

    @Override
    @Transactional
    public void doCallBackChangeList(List<String> codeList,
            String rollbackResult, String rollbackUser, String rollbackNote,
            String systemCode) {
        for (String code : codeList) {
            this.doCallBackChange(code, rollbackResult, rollbackUser,
                rollbackNote, systemCode);
        }
    }

    /*
     * 人工调账： 1、判断流水账是否平，平则更改订单状态，不平则更改产生红冲蓝补订单，而后更改订单状态
     */
    @Override
    @Transactional
    public void checkJour(String code, Long checkAmount, String checkUser,
            String checkNote, String systemCode) {
        Jour data = jourBO.getJour(code, systemCode);
        if (!EJourStatus.todoCheck.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该单号不处于待对账状态");
        }
        if (checkAmount != 0) {
            Account account = accountBO.getAccount(systemCode,
                data.getAccountNumber());
            String adjustCode = jourBO
                .addAdjustJour(account, code, checkAmount);
            checkNote = checkNote + ",调账单号[" + adjustCode + "]";
            jourBO.doCheckJour(code, EBoolean.NO, checkUser, checkNote);
        } else {
            jourBO.doCheckJour(code, EBoolean.YES, checkUser, checkNote);
        }
    }

    @Override
    @Transactional
    public void adjustJour(String code, String adjustResult, String adjustUser,
            String adjustNote, String systemCode) {
        Jour data = jourBO.getJour(code, systemCode);
        Account account = accountBO.getAccount(systemCode,
            data.getAccountNumber());
        if (!EJourStatus.todoAdjust.getCode().equals(data.getStatus())) {
            throw new BizException("xn000000", "该单号不处于调账待审核状态");
        }
        // 审核通过，账户钱处理
        Date date = new Date();
        Long preAmount = account.getAmount();
        Long postAmount = preAmount + data.getTransAmount();
        if (EBoolean.YES.getCode().equals(adjustResult)) {
            accountBO.transAmountNotJour(systemCode, data.getAccountNumber(),
                data.getTransAmount(), code);
            jourBO.doAdjustJour(code, EBoolean.YES, adjustUser, date,
                adjustNote, preAmount, postAmount);
            // 系统账户划转
            EBizType eBizType = EBizType.AJ_HC;
            if (data.getTransAmount() > 0) {
                eBizType = EBizType.AJ_LB;
            }
            Account sysAccount = accountBO.getSysAccount(
                ESysUser.SYS_USER.getCode(), account.getCurrency());
            accountBO.transAmount(systemCode, sysAccount.getAccountNumber(),
                EChannelType.Adjust_ZH, null, -data.getTransAmount(),
                eBizType.getCode(), eBizType.getValue() + "单号[" + code + "]");
        } else {
            jourBO.doAdjustJour(code, EBoolean.NO, adjustUser, date,
                adjustNote, null, null);
        }
        jourBO.refreshOrderStatus(data.getChannelOrder(), adjustUser, date,
            adjustNote);
    }

    @Override
    public Paginable<Jour> queryJourPage(int start, int limit, Jour condition) {
        // 处理bizType=52,54
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }
        return jourBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Jour> queryJourList(Jour condition) {
        // 处理bizType=52,54
        String bizType = condition.getBizType();
        if (StringUtils.isNotBlank(bizType)) {
            String[] bizTypeArrs = bizType.split(",");
            List<String> bizTypeList = new ArrayList<String>();
            for (int i = 0; i < bizTypeArrs.length; i++) {
                bizTypeList.add(bizTypeArrs[i]);
            }
            condition.setBizType(null);
            condition.setBizTypeList(bizTypeList);
        }
        return jourBO.queryJourList(condition);
    }

    /** 
     * @see com.std.account.ao.IJourAO#getJour(java.lang.String)
     */
    @Override
    public Jour getJour(String code, String systemCode) {
        return jourBO.getJour(code, systemCode);
    }
}
