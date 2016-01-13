/**
 * @Title IBankDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午3:08:54 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IBankDAO;
import com.std.account.domain.Bank;
import com.std.account.enums.EBankType;
import com.std.account.enums.EBoolean;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午3:08:54 
 * @history:
 */
public class IBankDAOTest extends ADAOTest {
    @SpringBeanByType
    private IBankDAO bankDAO;

    @Test
    public void insert() {
        Bank data = new Bank();
        data.setBankNo("ABC");
        data.setBankName("中国农业银行");
        data.setBankType(EBankType.STATE.getCode());
        data.setQuickType(EBoolean.YES.getCode());
        data.setIsEnable(EBoolean.YES.getCode());
        data.setChannelNo("01");
        int lineNum = bankDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void delete() {
        Bank data = new Bank();
        data.setBankNo("ABC");
        int lineNum = bankDAO.delete(data);
        logger.info("delete : {}", lineNum);
    }

    @Test
    public void update() {
        Bank data = new Bank();
        data.setBankNo("ABC");
        data.setBankName("中国农业银行");
        data.setBankType(EBankType.STATE.getCode());
        data.setQuickType(EBoolean.YES.getCode());
        data.setIsEnable(EBoolean.YES.getCode());
        data.setChannelNo("02");
        int lineNum = bankDAO.update(data);
        logger.info("update : {}", lineNum);
    }

    @Test
    public void select() {
        Bank data = new Bank();
        data.setBankNo("ABC");
        data = bankDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        Bank data = new Bank();
        data.setBankNo("ABC");
        long id = bankDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        Bank data = new Bank();
        data.setBankNo("ABC");
        List<Bank> dataList = bankDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        Bank data = new Bank();
        data.setBankNo("ABC");
        List<Bank> dataList = bankDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

}
