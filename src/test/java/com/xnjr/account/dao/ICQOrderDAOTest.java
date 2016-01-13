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

import com.std.account.dao.ICQOrderDAO;
import com.std.account.domain.CQOrder;
import com.std.account.enums.EBankCode;
import com.std.account.enums.EChannel;
import com.std.account.enums.EDirection;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EUser;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午3:18:51 
 * @history:
 */
public class ICQOrderDAOTest extends ADAOTest {
    @SpringBeanByType
    private ICQOrderDAO cqOrderDAO;

    @Test
    public void insert() {
        CQOrder data = new CQOrder();
        data.setCqNo("1L");
        data.setStatus(EOrderStatus.UNAPPROVE.getCode());
        data.setDirection(EDirection.PLUS.getCode());
        data.setChannel(EChannel.OFFLINE.getCode());
        data.setAmount(1000L);

        data.setBankCode(EBankCode.APLIPAY.getCode());
        data.setBankcardNo("myb858@hotmail.com");
        data.setCreateDatetime(new Date());
        data.setRemark("test");
        data.setAccountNumber("1");
        int lineNum = cqOrderDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        CQOrder data = new CQOrder();
        data.setCqNo("1L");
        data = cqOrderDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        CQOrder data = new CQOrder();
        data.setCqNo("1L");
        long id = cqOrderDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        CQOrder data = new CQOrder();
        data.setCqNo("1L");
        List<CQOrder> dataList = cqOrderDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        CQOrder data = new CQOrder();
        data.setCqNo("1L");
        List<CQOrder> dataList = cqOrderDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void updateApproveOrder() {
        CQOrder data = new CQOrder();
        data.setCqNo("1L");
        data.setApproveUser(EUser.LI.getCode());
        data.setApproveDatetime(new Date());
        data.setStatus(EOrderStatus.APPROVE_NO.getCode());
        data.setRemark("test approve");
        int count = cqOrderDAO.updateApproveOrder(data);
        logger.info("updateApproveOrder : {}", count);
    }

    @Test
    public void updatePayOrder() {
        CQOrder data = new CQOrder();
        data.setCqNo("1L");
        data.setPayUser(EUser.LI.getCode());
        data.setPayDatetime(new Date());
        data.setPayNo("X123453322");
        data.setPayFee(1000L);
        data.setStatus(EOrderStatus.PAY_NO.getCode());
        data.setRemark("test pay");
        data.setWorkDate("20140101");
        int count = cqOrderDAO.updatePayOrder(data);
        logger.info("updatePayOrder : {}", count);
    }

    @Test
    public void updateCheckOrder() {
        CQOrder data = new CQOrder();
        data.setCqNo("1L");
        data.setCheckUser(EUser.LI.getCode());
        data.setCheckDatetime(new Date());
        data.setStatus(EOrderStatus.CHECKED_NO.getCode());
        data.setRemark("test check");
        int count = cqOrderDAO.updateCheckOrder(data);
        logger.info("updatePayOrder : {}", count);
    }
}
