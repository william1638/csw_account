package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IUserLoginLogDAO;
import com.std.account.domain.UserLoginLog;
import com.xnjr.account.base.ADAOTest;

/**
 * 
 * @author: luoqi 
 * @since: 2015年3月7日 下午2:36:10 
 * @history:
 */
public class IUserLoginLogDAOTest extends ADAOTest {
    @SpringBeanByType
    private IUserLoginLogDAO userLoginLogDao;

    @Test
    public void insert() {
        UserLoginLog data = new UserLoginLog();
        data.setUserId("1");
        data.setLoginDatetime(new Date());
        data.setLoginIp("192.89.83.33");
        data.setIsSuccess("0");
        int lineNum = userLoginLogDao.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void selectLatestUserLoginLog() {
        UserLoginLog data = new UserLoginLog();
        data.setUserId("20150924411322316535611");
        UserLoginLog log = userLoginLogDao.selectLatestUserLoginLog(data);
        logger.info("selectLatestUserLoginLog : {}", log);
    }

    @Test
    public void selectTotalCount() {
        UserLoginLog data = new UserLoginLog();
        data.setUserId("1");
        long id = userLoginLogDao.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        UserLoginLog data = new UserLoginLog();
        data.setIsSuccess("1");
        List<UserLoginLog> dataList = userLoginLogDao.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        UserLoginLog data = new UserLoginLog();
        data.setIsSuccess("1");
        List<UserLoginLog> dataList = userLoginLogDao.selectList(data, 1, 1);
        logger.info("selectPage : {}", dataList);
    }
}
