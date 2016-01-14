package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IDictDAO;
import com.std.account.domain.Dict;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: xieyj 
 * @since: 2015-2-25 下午4:41:52 
 * @history:
 */
public class IDictDAOTest extends ADAOTest {
    @SpringBeanByType
    private IDictDAO dictDao;

    @Test
    public void insert() {
        Dict data = new Dict();
        data.setpId(2L);
        data.setKey("sex");
        data.setValue("性别");
        data.setRemark("remark");
        data.setCreator("xieyj");
        data.setCreateDatetime(new Date());

        int lineNum = dictDao.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        Dict data = new Dict();
        data.setKey("1");
        data = dictDao.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        Dict data = new Dict();
        data.setKey("1");

        long id = dictDao.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        Dict data = new Dict();
        data.setKey("1");

        List<Dict> dataList = dictDao.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        Dict data = new Dict();
        data.setKey("1");

        List<Dict> dataList = dictDao.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void update() {
        Dict data = new Dict();
        data.setId(2L);
        data.setpId(123L);
        data.setKey("sex1");
        data.setValue("性别1");
        data.setRemark("remark1");
        data.setUpdater("updater");
        data.setUpdateDatetime(new Date());
        int count = dictDao.update(data);
        logger.info("update : {}", count);
    }
}
