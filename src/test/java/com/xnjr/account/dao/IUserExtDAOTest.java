package com.xnjr.account.dao;

import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IUserExtDAO;
import com.std.account.domain.UserExt;
import com.xnjr.account.base.ADAOTest;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午9:17:14 
 * @history:
 */
public class IUserExtDAOTest extends ADAOTest {
    @SpringBeanByType
    private IUserExtDAO userExtDao;

    @Test
    public void insert() {
        UserExt data = new UserExt();
        data.setUserId("1");
        data.setPhoto("pictures");
        data.setComefrom("china");
        data.setBirthday("19990802");
        data.setQq("4236384682");
        data.setEmail("jdlgj@hotmail.com");
        data.setOccupation("职业");
        data.setEducation("本科");
        int lineNum = userExtDao.insert(data);
        logger.info("insert : {}", lineNum);
    }

    // @Test
    // public void delete() {
    // UserExt data = new UserExt();
    // data.setUserId("1");
    // int lineNum = userExtDao.delete(data);
    // logger.info("delete : {}", lineNum);
    // }

    @Test
    public void select() {
        UserExt data = new UserExt();
        data.setUserId("1");
        data.setPhoto("pictures");
        data.setComefrom("china");
        data.setBirthday("19990802");
        data.setQq("4236384682");
        data.setEmail("jdlgj@hotmail.com");
        data.setOccupation("职业");
        data.setEducation("本科");
        data = userExtDao.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        UserExt data = new UserExt();
        data.setUserId("1");
        data.setPhoto("pictures");
        data.setComefrom("china");
        data.setBirthday("19990802");
        data.setQq("4236384682");
        data.setEmail("jdlgj@hotmail.com");
        data.setOccupation("职业");
        data.setEducation("本科");
        long id = userExtDao.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        UserExt data = new UserExt();
        data.setUserId("1");
        data.setPhoto("pictures");
        data.setComefrom("china");
        data.setBirthday("19990802");
        data.setQq("4236384682");
        data.setEmail("jdlgj@hotmail.com");
        data.setOccupation("职业");
        data.setEducation("本科");
        List<UserExt> dataList = userExtDao.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        UserExt data = new UserExt();
        data.setUserId("1");
        data.setPhoto("pictures");
        data.setComefrom("china");
        data.setBirthday("19990802");
        data.setQq("4236384682");
        data.setEmail("jdlgj@hotmail.com");
        data.setOccupation("职业");
        data.setEducation("本科");
        List<UserExt> dataList = userExtDao.selectList(data, 1, 1);
        logger.info("selectPage : {}", dataList);
    }
}
