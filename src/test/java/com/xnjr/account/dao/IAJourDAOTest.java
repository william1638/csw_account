/**
 * @Title IIAJourDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午2:27:03 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IAJourDAO;
import com.std.account.domain.AccountJour;
import com.std.account.enums.EBizType;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EUser;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午2:27:03 
 * @history:
 */
public class IAJourDAOTest extends ADAOTest {
    @SpringBeanByType
    private IAJourDAO aJourDAO;

    @Test
    public void insert() {
        AccountJour data = new AccountJour();
        // data.setAjNo(1L);
        data.setStatus(EOrderStatus.PAY_YES.getCode());
        data.setBizType(EBizType.AJ_QX.getCode());
        data.setRefNo("");
        data.setTransAmount(1L);

        data.setPreAmount(0L);
        data.setPostAmount(1L);
        data.setRemark("test");
        data.setCreateDatetime(new Date());
        data.setWorkDate("20150219");

        data.setAccountNumber("1");
        int lineNum = aJourDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        AccountJour data = new AccountJour();
        data.setAjNo(1L);
        data = aJourDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        AccountJour data = new AccountJour();
        data.setAjNo(1L);
        long id = aJourDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        AccountJour data = new AccountJour();
        data.setAjNo(1L);
        List<AccountJour> dataList = aJourDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        AccountJour data = new AccountJour();
        data.setAjNo(1L);
        List<AccountJour> dataList = aJourDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void updateCheckInfo() {
        AccountJour data = new AccountJour();
        data.setAjNo(1L);
        data.setStatus(EOrderStatus.CHECKED_YES.getCode());
        data.setCheckUser(EUser.LI.getCode());
        data.setCheckDatetime(new Date());
        data.setRemark("test check");
        int count = aJourDAO.updateCheckInfo(data);
        logger.info("updateCheckInfo : {}", count);
    }

}
