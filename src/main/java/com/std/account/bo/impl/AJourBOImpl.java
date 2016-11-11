/**
 * @Title AJourBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:22:07 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IAJourBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.common.DateUtil;
import com.std.account.dao.IAJourDAO;
import com.std.account.domain.AccountJour;
import com.std.account.enums.EAccountJourStatus;
import com.std.account.enums.EBoolean;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:22:07 
 * @history:
 */
@Component
public class AJourBOImpl extends PaginableBOImpl<AccountJour> implements
        IAJourBO {
    @Autowired
    private IAJourDAO aJourDAO;

    @Override
    public AccountJour getAccountJour(String order) {
        AccountJour data = null;
        if (StringUtils.isNotBlank(order)) {
            AccountJour condition = new AccountJour();
            condition.setOrder(order);
            data = aJourDAO.select(condition);
        }
        return data;
    }

    @Override
    public void doCheckAccount(String order, String checkUser,
            EBoolean checkResult) {
        if (StringUtils.isNotBlank(order) && StringUtils.isNotBlank(checkUser)) {
            AccountJour data = new AccountJour();
            data.setOrder(order);
            data.setCheckUser(checkUser);
            data.setCheckDatetime(new Date());
            if (EBoolean.YES.getCode().equalsIgnoreCase(checkResult.getCode())) {
                data.setStatus(EAccountJourStatus.Checked_YES.getCode());
            } else {
                data.setStatus(EAccountJourStatus.Checked_NO.getCode());
            }
            aJourDAO.doCheckAccount(data);
        }
    }

    /** 
     * @see com.xnjr.moom.bo.IFDAJourBO#addJour(java.lang.String, java.lang.Long, java.lang.Long, com.xnjr.moom.enums.EBizType, java.lang.String)
     */
    @Override
    public void addJour(String accountNumber, Long preAmount, Long amount,
            String bizType, String refNo, String remark) {
        Long postAmount = preAmount + amount;
        AccountJour accountJour = new AccountJour();
        accountJour.setStatus(EAccountJourStatus.todoCheck.getCode());
        accountJour.setBizType(bizType);
        accountJour.setTransAmount(amount);
        accountJour.setPreAmount(preAmount);
        accountJour.setPostAmount(postAmount);
        accountJour.setRemark(remark);
        accountJour.setWorkDate(DateUtil
            .getToday(DateUtil.DB_DATE_FORMAT_STRING));
        accountJour.setAccountNumber(accountNumber);
        aJourDAO.insert(accountJour);
    }

    /** 
     * @see com.std.account.bo.IAJourBO#doAdjustAccount(java.lang.String, java.lang.String, com.std.account.enums.EBoolean)
     */
    @Override
    public void doAdjustAccount(String order, String adjustUser,
            EBoolean adjustResult) {
    }

    /** 
     * @see com.std.account.bo.IAJourBO#doTransAccount(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doTransAccount(String order, String status, String payOrder) {
        // TODO Auto-generated method stub

    }
}
