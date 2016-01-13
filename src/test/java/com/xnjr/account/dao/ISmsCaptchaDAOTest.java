package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.ISmsCaptchaDAO;
import com.std.account.domain.SmsCaptcha;
import com.std.account.enums.ESmsBizType;
import com.std.account.enums.ESmsStatus;
import com.xnjr.account.base.ADAOTest;

/**
 * @author: xieyj 
 * @since: 2015-3-7 下午3:04:04 
 * @history:
 */
public class ISmsCaptchaDAOTest extends ADAOTest {
    @SpringBeanByType
    private ISmsCaptchaDAO smsCaptchaDAO;

    // @Test
    // public void delete() {
    // SmsCaptcha data = new SmsCaptcha();
    // data.setId(1l);
    // int lineNum = smsCaptchaDAO.delete(data);
    // logger.info("delete : {}", lineNum);
    // }
    //
    // @Test
    // public void insert() {
    // SmsCaptcha data = new SmsCaptcha();
    // data.setId(1l);
    // data.setMobile("15158117100");
    // data.setBizType(ESmsBizType.REGISTER.getCode());
    // data.setSmsCaptcha("8888");
    // data.setStatus(ESmsStatus.TOSEND.getCode());// 0 待发送 1 已发送
    // Date date = new Date();
    // data.setCreateDatetime(new Date());
    // Calendar c = Calendar.getInstance();
    // c.setTime(date);
    // c.add(Calendar.MINUTE, 5);// 有效时间5分钟
    // data.setInvalidDatetime(c.getTime());
    // int lineNum = smsCaptchaDAO.insert(data);
    // logger.info("insert : {}", lineNum);
    // }
    //
    // @Test
    // public void select() {
    // SmsCaptcha data = new SmsCaptcha();
    // data.setId(1l);
    // data.setMobile("15158117100");
    // data.setBizType(ESmsBizType.REGISTER.getCode());
    // data.setSmsCaptcha("8888");
    // data.setStatus(ESmsStatus.TOSEND.getCode());// 0 待发送 1 已发送
    // data = smsCaptchaDAO.select(data);
    // logger.info("select : {}", data);
    // }
    //
    // @Test
    // public void selectTotalCount() {
    // SmsCaptcha data = new SmsCaptcha();
    // data.setId(1l);
    // data.setMobile("15158117100");
    // data.setBizType(ESmsBizType.REGISTER.getCode());
    // data.setSmsCaptcha("8888");
    // data.setStatus(ESmsStatus.TOSEND.getCode());// 0 待发送 1 已发送
    // long id = smsCaptchaDAO.selectTotalCount(data);
    // logger.info("selectTotalCount : {}", id);
    // }

    @Test
    public void selectList() {
        SmsCaptcha data = new SmsCaptcha();
        data.setMobile("13958092437");
        data.setSmsCaptcha("5021");
        data.setBizType(ESmsBizType.FINDLOGINPWD.getCode());
        data.setStatus(ESmsStatus.SENT_YES.getCode());
        data.setInvalidDatetime(new Date());
        data.setCheckTimes(3);
        List<SmsCaptcha> dataList = smsCaptchaDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    // @Test
    // public void selectPage() {
    // SmsCaptcha data = new SmsCaptcha();
    // data.setId(1l);
    // data.setMobile("15158117100");
    // data.setBizType(ESmsBizType.REGISTER.getCode());
    // data.setStatus(ESmsStatus.TOSEND.getCode());// 0 待发送 1 已发送
    // List<SmsCaptcha> dataList = smsCaptchaDAO.selectList(data, 0, 1);
    // logger.info("selectPage : {}", dataList);
    // }

}
