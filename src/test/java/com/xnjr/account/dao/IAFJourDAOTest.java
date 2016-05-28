/**
 * @Title IAFJourDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午1:59:40 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IAFJourDAO;
import com.std.account.domain.AccountFrozenJour;
import com.std.account.enums.EBizType;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午1:59:40 
 * @history:
 */
public class IAFJourDAOTest extends ADAOTest {
    @SpringBeanByType
    private IAFJourDAO afJourDAO;

    @Test
    public void insert() {
        AccountFrozenJour data = new AccountFrozenJour();
        // data.setAfjNo(1L);
        data.setBizType(EBizType.AJ_CZ.getCode());
        data.setRefNo("1L");
        data.setTransAmount(1L);
        data.setPreAmount(0L);

        data.setPostAmount(1L);
        data.setRemark("test");
        data.setCreateDatetime(new Date());
        data.setAccountNumber("1");
        int lineNum = afJourDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        AccountFrozenJour data = new AccountFrozenJour();
        data.setAfjNo(1L);
        data = afJourDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        AccountFrozenJour data = new AccountFrozenJour();
        data.setAfjNo(1L);
        long id = afJourDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        AccountFrozenJour data = new AccountFrozenJour();
        data.setAfjNo(1L);
        List<AccountFrozenJour> dataList = afJourDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        AccountFrozenJour data = new AccountFrozenJour();
        data.setAfjNo(1L);
        List<AccountFrozenJour> dataList = afJourDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

}
