package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IChargeDAO;
import com.std.account.domain.Charge;
import com.std.account.enums.EChannel;
import com.std.account.enums.EFromType;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EUser;
import com.xnjr.account.base.ADAOTest;

public class IChargeDAOTest extends ADAOTest {
    @SpringBeanByType
    private IChargeDAO chargeDAO;

    @Test
    public void insert() {
        Charge data = new Charge();
        String code = OrderNoGenerater.generate("C");
        data.setCode(code);
        data.setFromType(EFromType.Alipay.getCode());
        data.setFromCode("fromCode");
        data.setChannel(EChannel.OFFLINE.getCode());

        data.setAmount(1000L);
        data.setCreateDatetime(new Date());
        data.setStatus(EOrderStatus.todoAPPROVE.getCode());
        data.setAccountNumber("accountNumber");

        int lineNum = chargeDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        Charge data = new Charge();
        data.setCode("1L");
        data = chargeDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        Charge data = new Charge();
        data.setCode("1L");
        long id = chargeDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        Charge data = new Charge();
        data.setCode("1L");
        List<Charge> dataList = chargeDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        Charge data = new Charge();
        data.setCode("1L");
        List<Charge> dataList = chargeDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void updateApproveOrder() {
        Charge data = new Charge();
        data.setCode("C2016052810383111152");
        data.setApproveUser(EUser.LI.getCode());
        data.setApproveNote("test approve");
        data.setApproveDatetime(new Date());
        data.setStatus(EOrderStatus.APPROVE_NO.getCode());
        int count = chargeDAO.updateApproveOrder(data);
        logger.info("updateApproveOrder : {}", count);
    }

}
