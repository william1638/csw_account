/**
 * @Title IChannelDAOTest.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午3:08:40 
 * @version V1.0   
 */
package com.xnjr.account.dao;

import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IChannelDAO;
import com.std.account.domain.Channel;
import com.std.account.enums.EBoolean;
import com.xnjr.account.base.ADAOTest;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午3:08:40 
 * @history:
 */
public class IChannelDAOTest extends ADAOTest {
    @SpringBeanByType
    private IChannelDAO channelDAO;

    @Test
    public void insert() {
        Channel data = new Channel();
        data.setChannelNo("01");
        data.setChannelName("易宝支付");
        data.setChannelStatus(EBoolean.YES.getCode());
        data.setRemark("测试");
        int lineNum = channelDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void delete() {
        Channel data = new Channel();
        data.setChannelNo("01");
        int lineNum = channelDAO.delete(data);
        logger.info("delete : {}", lineNum);
    }

    @Test
    public void select() {
        Channel data = new Channel();
        data.setChannelNo("01");
        data = channelDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        Channel data = new Channel();
        data.setChannelNo("01");
        long id = channelDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        Channel data = new Channel();
        data.setChannelNo("01");
        List<Channel> dataList = channelDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        Channel data = new Channel();
        data.setChannelNo("01");
        List<Channel> dataList = channelDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

}
