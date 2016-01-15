package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.ICompanyDAO;
import com.std.account.domain.Company;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: wuql
 * @since: 2016年1月14日 下午2:53:53 
 * @history:
 */
public class ICompanyDAOTest extends ADAOTest {
    @SpringBeanByType
    private ICompanyDAO companyDao;

    @Test
    public void insert() {
        Company data = new Company();
        data.setCompanyId("11111113");
        data.setCompanyName("公司名称");
        data.setLicenceNo("11");
        data.setIdKind("1");
        data.setIdNo("11111111");
        data.setRealName("李丽丽");
        data.setCapital(1L);
        data.setProvince("浙江");
        data.setCity("hangzhou");
        data.setAddress("tianshicun");
        data.setGsyyzzPicture("wwww");
        data.setZzjgdmzPicture("wwww");
        data.setSwdjzPicture("www");
        data.setDzzPicture("www");
        data.setSqghPicture("www");
        data.setFrPicture("www");
        data.setOtherPicture("wwww");
        data.setApplyDatetime(new Date());
        data.setKycDatetime(new Date());
        data.setStatus("1");
        data.setRemark("111");
        int lineNum = companyDao.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        Company data = new Company();
        data.setCompanyId("11111111");
        data = companyDao.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        Company data = new Company();
        data.setCompanyId("11111111");
        long id = companyDao.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        Company data = new Company();
        data.setCompanyId("11111111");
        List<Company> dataList = companyDao.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        Company data = new Company();
        data.setCompanyId("11111111");
        List<Company> dataList = companyDao.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

    @Test
    public void update() {
        Company data = new Company();
        data.setRemark("xiugai");
        int count = companyDao.update(data);
        logger.info("update : {}", count);
    }
}
