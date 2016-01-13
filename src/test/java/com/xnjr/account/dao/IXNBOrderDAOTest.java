/**
 * @Title IXNBOrderDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午4:14:03 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IXNBOrderDAO;
import com.std.account.domain.XNBOrder;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EUser;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午4:14:03 
 * @history:
 */
public class IXNBOrderDAOTest extends ADAOTest {
    @SpringBeanByType
    private IXNBOrderDAO xnbOrderDAO;

    @Test
    public void insert() {
        XNBOrder data = new XNBOrder();
        data.setXnbNo("1L");
        data.setStatus(EOrderStatus.UNAPPROVE.getCode());
        data.setType("1");
        data.setScore(100L);
        data.setAmount(100000L);

        data.setRemark("test");
        data.setCreateDatetime(new Date());
        data.setAccountNumber("1");
        int lineNum = xnbOrderDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        XNBOrder data = new XNBOrder();
        data.setXnbNo("1L");
        data = xnbOrderDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        XNBOrder data = new XNBOrder();
        data.setXnbNo("1L");
        long id = xnbOrderDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        XNBOrder data = new XNBOrder();
        data.setXnbNo("1L");
        List<XNBOrder> dataList = xnbOrderDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        XNBOrder data = new XNBOrder();
        data.setXnbNo("1L");
        List<XNBOrder> dataList = xnbOrderDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void updateApproveOrder() {
        XNBOrder data = new XNBOrder();
        data.setXnbNo("1L");
        data.setStatus(EOrderStatus.UNAPPROVE.getCode());
        data.setApproveUser(EUser.LI.getCode());
        data.setApproveDatetime(new Date());
        data.setRemark("test approve");
        int count = xnbOrderDAO.updateApproveOrder(data);
        logger.info("updateApproveOrder : {}", count);
    }

}
