package com.xnjr.account.dao;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.unitils.spring.annotation.SpringBeanByType;

import com.std.account.dao.IBankCardDAO;
import com.std.account.domain.BankCard;
import com.std.account.enums.EBankCardStatus;
import com.std.account.enums.EBankCode;
import com.xnjr.account.base.ADAOTest;

/**
 * @author: xieyj 
 * @since: 2015-3-7 下午3:04:04 
 * @history:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IBankCardDAOTest extends ADAOTest {
    @SpringBeanByType
    private IBankCardDAO bankCardDAO;

    @Test
    public void insert() {
        BankCard data = new BankCard();
        data.setUserId("2");
        data.setBankCode(EBankCode.ABC.getCode());
        data.setBankName(EBankCode.ABC.getValue());
        data.setBankCardNo("111111");
        data.setStatus(EBankCardStatus.TOCONFIRM.getCode());// 0 待确认 1 验证通过
        data.setCreateDatetime(new Date());
        int lineNum = bankCardDAO.insert(data);
        logger.info("insert : {}", lineNum);
    }

    @Test
    public void delete() {
        BankCard data = new BankCard();
        data.setUserId("2");
        data.setBankCardNo("111111");
        int lineNum = bankCardDAO.delete(data);
        logger.info("delete : {}", lineNum);
    }

    @Test
    public void updateStatus() {
        BankCard data = new BankCard();
        data.setUserId("2");
        data.setBankCardNo("111111");
        data.setStatus(EBankCardStatus.CONFIRM_YES.getCode());// 1 验证通过
        data.setUpdateDatetime(new Date());
        int lineNum = bankCardDAO.updateStatus(data);
        logger.info("updateStatus : {}", lineNum);
    }

    @Test
    public void select() {
        BankCard data = new BankCard();
        data.setUserId("2");
        // 这里缺少一张银行信息管理表
        data.setBankCode(EBankCode.ABC.getCode());
        data.setBankName(EBankCode.ABC.getValue());
        data.setBankCardNo("111111");
        data.setStatus(EBankCardStatus.TOCONFIRM.getCode());// 0 待确认 1 验证通过
        data = bankCardDAO.select(data);
        logger.info("select : {}", data);
    }

    @Test
    public void selectTotalCount() {
        BankCard data = new BankCard();
        data.setUserId("2");
        long id = bankCardDAO.selectTotalCount(data);
        logger.info("selectTotalCount : {}", id);
    }

    @Test
    public void selectList() {
        BankCard data = new BankCard();
        data.setUserId("2");
        data.setBankCode(EBankCode.ABC.getCode());
        data.setBankName(EBankCode.ABC.getValue());
        data.setBankCardNo("111111");
        data.setStatus(EBankCardStatus.TOCONFIRM.getCode());// 0 待确认 1 验证通过
        List<BankCard> dataList = bankCardDAO.selectList(data);
        logger.info("selectList : {}", dataList);
    }

    @Test
    public void selectPage() {
        BankCard data = new BankCard();
        data.setUserId("2");
        data.setBankCode(EBankCode.ABC.getCode());
        data.setBankName(EBankCode.ABC.getValue());
        data.setBankCardNo("111111");
        data.setStatus(EBankCardStatus.TOCONFIRM.getCode());// 0 待确认 1 验证通过
        List<BankCard> dataList = bankCardDAO.selectList(data, 0, 1);
        logger.info("selectPage : {}", dataList);
    }

}
