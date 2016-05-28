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
    public AccountJour getAccountJour(Long ajNo) {
        AccountJour data = null;
        if (ajNo > 0) {
            AccountJour condition = new AccountJour();
            condition.setAjNo(ajNo);
            data = aJourDAO.select(condition);
        }
        return data;
    }

    @Override
    public void doCheckAccount(Long aJNo, String checkUser, EBoolean checkResult) {
        if (aJNo > 0 && StringUtils.isNotBlank(checkUser)) {
            AccountJour data = new AccountJour();
            data.setAjNo(aJNo);
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

}
