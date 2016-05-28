/**
 * @Title IZZOrderDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午4:33:15 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.core.OrderNoGenerater;
import com.std.account.dao.IZZOrderDAO;
import com.std.account.domain.ZZOrder;
import com.std.account.enums.EDirection;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午4:33:15 
 * @history:
 */
public class IZZOrderDAOTest extends ADAOTest {
    @SpringBeanByType
    private IZZOrderDAO zzOrderDAO;

    @Test
    public void insert() {
        ZZOrder data = new ZZOrder();
        String code = OrderNoGenerater.generate("ZZ");
        data.setCode(code);
        data.setDirection(EDirection.PLUS.getCode());
        data.setAmount(100000L);
        data.setFee(100L);
        data.setRemark("test");

        data.setCreateDatetime(new Date());
        data.setAccountNumber("1");
        int lineNum = zzOrderDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void select() {
        ZZOrder data = new ZZOrder();
        data.setCode("setZzOrderNo");
        data = zzOrderDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        ZZOrder data = new ZZOrder();
        data.setCode("setZzOrderNo");
        long id = zzOrderDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        ZZOrder data = new ZZOrder();
        data.setCode("setZzOrderNo");
        List<ZZOrder> dataList = zzOrderDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        ZZOrder data = new ZZOrder();
        data.setCode("setZzOrderNo");
        List<ZZOrder> dataList = zzOrderDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

}
