/**
 * @Title ICQOrderDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午3:18:51 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IWithdrawDAO;
import com.std.account.domain.Withdraw;
import com.std.account.enums.EChannelType;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EToType;
import com.std.account.enums.EUser;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午3:18:51 
 * @history:
 */
public class IWithdrawDAOTest extends ADAOTest {
    @SpringBeanByType
    private IWithdrawDAO withdrawDAO;

    @Test
    public void insert() {

        Withdraw data = new Withdraw();
        String code = OrderNoGenerater.generate("C");
        data.setCode(code);
        data.setToType(EToType.BankCard.getCode());
        data.setToCode("myb858@hotmail.com");
        data.setChannel(EChannelType.OFFLINE.getCode());

        data.setAmount(1000L);
        data.setCreateDatetime(new Date());
        data.setStatus(EOrderStatus.todoAPPROVE.getCode());
        data.setAccountNumber("accountNumber");
        int lineNum = withdrawDAO.insert(data);

        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        Withdraw data = new Withdraw();
        data.setCode("1L");
        data = withdrawDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        Withdraw data = new Withdraw();
        data.setCode("1L");
        long id = withdrawDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        Withdraw data = new Withdraw();
        data.setCode("1L");
        List<Withdraw> dataList = withdrawDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        Withdraw data = new Withdraw();
        data.setCode("1L");
        List<Withdraw> dataList = withdrawDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void updateApproveOrder() {
        Withdraw data = new Withdraw();
        data.setCode("C2016052810383111152");
        data.setApproveUser(EUser.LI.getCode());
        data.setApproveNote("test approve");
        data.setApproveDatetime(new Date());
        data.setStatus(EOrderStatus.APPROVE_NO.getCode());
        int count = withdrawDAO.updateApproveOrder(data);
        logger.info("updateApproveOrder : {}", count);
    }

    @Test
    public void updatePayOrder() {
        Withdraw data = new Withdraw();
        data.setCode("C2016052810373137636");
        data.setPayUser(EUser.LI.getCode());
        data.setPayNote("test pay");
        data.setPayDatetime(new Date());
        data.setRefNo("X123453322");
        data.setFee(1000L);
        data.setStatus(EOrderStatus.PAY_NO.getCode());

        int count = withdrawDAO.updatePayOrder(data);
        logger.info("updatePayOrder : {}", count);
    }

}
