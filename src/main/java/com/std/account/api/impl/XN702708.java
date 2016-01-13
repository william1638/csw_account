/**
 * @Title XN702708.java 
 * @Package com.xnjr.account.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年12月12日 下午5:00:49 
 * @version V1.0   
 */
package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.domain.Account;
import com.std.account.dto.req.XN702708Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/** 
 * 主账户列表查询
 * @author: haiqingzheng 
 * @since: 2015年12月12日 下午5:00:49 
 * @history:
 */
public class XN702708 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN702708Req req = null;

    /** 
     * @see com.std.account.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Account condition = new Account();
        condition.setAccountNumber(req.getAccountNumber());
        condition.setStatus(req.getStatus());
        condition.setUserId(req.getUserId());
        condition.setMobile(req.getMobile());
        condition.setRealName(req.getRealName());

        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAccountAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return accountAO.queryAccountList(condition);
    }

    /** 
     * @see com.std.account.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702708Req.class);
    }

}
