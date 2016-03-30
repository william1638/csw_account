package com.std.account.api.impl;

import com.std.account.ao.ICompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 银行卡修改(企业或个人)
 * @author: xieyj 
 * @since: 2016年3月30日 下午2:22:18 
 * @history:
 */
public class XN801303 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

    }
}
