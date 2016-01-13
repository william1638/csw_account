/**
 * @Title IHLOrderDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午4:01:04 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IHLOrderDAO;
import com.std.account.domain.HLOrder;
import com.std.account.enums.EDirection;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EUser;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午4:01:04 
 * @history:
 */
public class IHLOrderDAOTest extends ADAOTest {
    @SpringBeanByType
    private IHLOrderDAO hlOrderDAO;

    @Test
    public void insert() {
        HLOrder data = new HLOrder();
        data.setHlNo("HlOrderNo");
        data.setStatus(EOrderStatus.UNAPPROVE.getCode());
        data.setDirection(EDirection.PLUS.getCode());
        data.setAmount(1000L);
        data.setApplyUser("miyb");

        data.setApplyNote("I am handsome");
        data.setCreateDatetime(new Date());
        data.setRemark("test");
        data.setAccountNumber("1");
        int lineNum = hlOrderDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        HLOrder data = new HLOrder();
        data.setHlNo("HlOrderNo");
        data = hlOrderDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        HLOrder data = new HLOrder();
        data.setHlNo("HlOrderNo");
        long id = hlOrderDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        HLOrder data = new HLOrder();
        data.setHlNo("HlOrderNo");
        List<HLOrder> dataList = hlOrderDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        HLOrder data = new HLOrder();
        data.setHlNo("HlOrderNo");
        List<HLOrder> dataList = hlOrderDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void updateApproveOrder() {
        HLOrder data = new HLOrder();
        data.setHlNo("HlOrderNo");
        data.setApproveUser(EUser.LI.getCode());
        data.setApproveDatetime(new Date());
        data.setStatus(EOrderStatus.UNAPPROVE.getCode());
        data.setRemark("test approve");
        int count = hlOrderDAO.updateApproveOrder(data);
        logger.info("updateApproveOrder : {}", count);
    }
}
