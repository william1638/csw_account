/**
 * @Title IYTOrderDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-5-9 下午3:04:42 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IYTOrderDAO;
import com.std.account.domain.YTOrder;
import com.std.account.enums.EBizType;
import com.std.account.enums.EOrderStatus;
import com.std.account.enums.EUser;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-5-9 下午3:04:42 
 * @history:
 */
public class IYTOrderDAOTest extends ADAOTest {
    @SpringBeanByType
    private IYTOrderDAO ytOrderDAO;

    @Test
    public void insert() {
        YTOrder data = new YTOrder();
        data.setYtNo("HlOrderNo");
        data.setStatus(EOrderStatus.UNAPPROVE.getCode());
        data.setBizType(EBizType.AJ_QX.getCode());
        data.setAmount(1000L);
        data.setRemark("test");

        data.setCreateDatetime(new Date());
        data.setAccountNumber("1");
        int lineNum = ytOrderDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        YTOrder data = new YTOrder();
        data.setYtNo("HlOrderNo");
        data = ytOrderDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        YTOrder data = new YTOrder();
        data.setYtNo("HlOrderNo");
        long id = ytOrderDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        YTOrder data = new YTOrder();
        data.setYtNo("HlOrderNo");
        List<YTOrder> dataList = ytOrderDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        YTOrder data = new YTOrder();
        data.setYtNo("HlOrderNo");
        List<YTOrder> dataList = ytOrderDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void updateApproveOrder() {
        YTOrder data = new YTOrder();
        data.setYtNo("HlOrderNo");
        data.setApproveUser(EUser.LI.getCode());
        data.setApproveDatetime(new Date());
        data.setStatus(EOrderStatus.UNAPPROVE.getCode());
        data.setRemark("test approve");
        int count = ytOrderDAO.updateApproveOrder(data);
        logger.info("updateApproveOrder : {}", count);
    }
}
