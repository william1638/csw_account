package com.xnjr.account.dao;

import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IUserCompanyDAO;
import com.std.account.domain.UserCompany;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: wuql
 * @since: 2016年1月15日 下午2:08:18 
 * @history:
 */
public class IUserCompanyDAOTest extends ADAOTest {
    @SpringBeanByType
    private IUserCompanyDAO userCompanyDAO;

    @Test
    public void insert() {
        UserCompany data = new UserCompany();
        data.setUserId("8");
        data.setCompanyId("111");
        data.setRemark("插入成功");
        int lineNum = userCompanyDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void delete() {
        UserCompany data = new UserCompany();
        data.setId(7L);
        int lineNum = userCompanyDAO.delete(data);
        logger.info("delete : {}", lineNum);
    }

    @Test
    public void update() {
        UserCompany data = new UserCompany();
        data.setId(4L);
        data.setUserId("56");
        data.setCompanyId("555");
        int lineNum = userCompanyDAO.update(data);
        logger.info("update : {}", lineNum);
    }

    @Test
    public void select() {
        UserCompany data = new UserCompany();
        data.setId(1L);
        data = userCompanyDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        UserCompany data = new UserCompany();
        data.setUserId("1");
        long id = userCompanyDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        UserCompany data = new UserCompany();
        data.setUserId("2");
        List<UserCompany> dataList = userCompanyDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        UserCompany data = new UserCompany();
        data.setUserId("1");
        List<UserCompany> dataList = userCompanyDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

}
