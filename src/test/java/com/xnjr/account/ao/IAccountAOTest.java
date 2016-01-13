package com.xnjr.account.ao;

import org.junit.Test;

import com.std.account.ao.IAccountAO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Account;
import com.std.account.spring.SpringContextHolder;
import com.xnjr.account.base.ABizTest;

public class IAccountAOTest extends ABizTest {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    @Test
    public void queryAccountPage() {
        Account condition = new Account();
        condition.setOrder("account_number", true);
        Paginable<Account> page = accountAO.queryAccountPage(0, 2, condition);
        logger.info("queryAccountPage : {}", page);
    }
}
