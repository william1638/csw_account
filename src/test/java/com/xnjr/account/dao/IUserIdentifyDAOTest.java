package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IUserIdentifyDAO;
import com.std.account.domain.UserIdentify;
import com.xnjr.account.base.ADAOTest;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午9:37:39 
 * @history:
 */
public class IUserIdentifyDAOTest extends ADAOTest {
    @SpringBeanByType
    private IUserIdentifyDAO userIdentifyLogDao;

    @Test
    public void insert() {
        UserIdentify data = new UserIdentify();
        data.setUserId("1");
        data.setRealName("海清");
        data.setIdKind("1");
        data.setIdNo("43294729479294");
        data.setErrorCode("1");
        data.setErrorInfo("network error");
        data.setCreateDatetime(new Date());
        int lineNum = userIdentifyLogDao.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        UserIdentify data = new UserIdentify();
        data.setUserId("1");
        data.setRealName("海清");
        data.setIdKind("1");
        data.setIdNo("43294729479294");
        data.setErrorCode("1");
        data.setErrorInfo("network error");
        data = userIdentifyLogDao.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        UserIdentify data = new UserIdentify();
        data.setUserId("1");
        data.setRealName("海清");
        data.setIdKind("1");
        data.setIdNo("43294729479294");
        data.setErrorCode("1");
        data.setErrorInfo("network error");
        long id = userIdentifyLogDao.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        UserIdentify data = new UserIdentify();
        data.setUserId("1");
        data.setRealName("海清");
        data.setIdKind("1");
        data.setIdNo("43294729479294");
        data.setErrorCode("1");
        data.setErrorInfo("network error");
        List<UserIdentify> dataList = userIdentifyLogDao.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        UserIdentify data = new UserIdentify();
        data.setUserId("1");
        data.setRealName("海清");
        data.setIdKind("1");
        data.setIdNo("43294729479294");
        data.setErrorCode("1");
        data.setErrorInfo("network error");
        List<UserIdentify> dataList = userIdentifyLogDao.selectList(data, 1, 1);
        logger.info("selectPage : {}", dataList);
    }
}
