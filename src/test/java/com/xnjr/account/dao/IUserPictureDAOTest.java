package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IUserPictureDAO;
import com.std.account.domain.UserPicture;
import com.xnjr.account.base.ADAOTest;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午9:48:11 
 * @history:
 */
public class IUserPictureDAOTest extends ADAOTest {
    @SpringBeanByType
    private IUserPictureDAO userPictureDao;

    @Test
    public void insert() {
        UserPicture data = new UserPicture();
        data.setUserId("1");
        data.setIdPic1("picture1");
        data.setIdPic2("picture2");
        data.setIdUserPic("picture3");
        data.setCreateDatetime(new Date());
        data.setVerifyDatetime(new Date());
        data.setVerifyStatus("1");
        data.setVerifyUser("罗启");
        data.setRemark("备注信息");
        int lineNum = userPictureDao.insert(data);
        logger.info("insert : {}", lineNum);
    }

    // @Test
    // public void delete() {
    // UserPicture data = new UserPicture();
    // data.setUserId("1");
    // int lineNum = userPictureDao.delete(data);
    // logger.info("delete : {}", lineNum);
    // }

    @Test
    public void select() {
        UserPicture data = new UserPicture();
        data.setUserId("1");
        data.setIdPic1("picture1");
        data.setIdPic2("picture2");
        data.setIdUserPic("picture3");
        data.setVerifyStatus("1");
        data.setVerifyUser("罗启");
        data.setRemark("备注信息");
        data = userPictureDao.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        UserPicture data = new UserPicture();
        data.setUserId("1");
        data.setIdPic1("picture1");
        data.setIdPic2("picture2");
        data.setIdUserPic("picture3");
        data.setVerifyStatus("1");
        data.setVerifyUser("罗启");
        data.setRemark("备注信息");
        long id = userPictureDao.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        UserPicture data = new UserPicture();
        data.setUserId("1");
        data.setIdPic1("picture1");
        data.setIdPic2("picture2");
        data.setIdUserPic("picture3");
        data.setVerifyStatus("1");
        data.setVerifyUser("罗启");
        data.setRemark("备注信息");
        List<UserPicture> dataList = userPictureDao.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        UserPicture data = new UserPicture();
        data.setUserId("1");
        data.setIdPic1("picture1");
        data.setIdPic2("picture2");
        data.setIdUserPic("picture3");
        data.setVerifyStatus("1");
        data.setVerifyUser("罗启");
        data.setRemark("备注信息");
        List<UserPicture> dataList = userPictureDao.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }
}
