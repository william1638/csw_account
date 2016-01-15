/**
 * @Title IUserLockDAOTest.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-2-10 下午3:12:36 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IUserLockDAO;
import com.std.account.domain.UserLock;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-2-10 下午3:12:36 
 * @history:
 */
public class IUserLockDAOTest extends ADAOTest {
    @SpringBeanByType
    private IUserLockDAO userLockDAO;

    @Test
    public void insert() {
        UserLock data = new UserLock();
        data.setUserId("1");
        data.setLockDirection("1");// 1用锁;0解锁
        data.setRemark(new Date() + "因为某原因用锁3分钟");
        int lineNum = userLockDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        UserLock data = new UserLock();
        data.setUserId("1");
        data = userLockDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        UserLock data = new UserLock();
        data.setUserId("1");
        long id = userLockDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        UserLock data = new UserLock();
        data.setUserId("1");
        List<UserLock> dataList = userLockDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        UserLock data = new UserLock();
        data.setUserId("1");
        List<UserLock> dataList = userLockDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

}
