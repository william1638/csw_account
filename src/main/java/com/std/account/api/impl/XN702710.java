/**
 * @Title XN702710.java 
 * @Package com.xnjr.account.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年12月12日 下午5:01:32 
 * @version V1.0   
 */
package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IHLOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.domain.HLOrder;
import com.std.account.dto.req.XN702710Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/** 
 * 红冲蓝补记录查询
 * @author: haiqingzheng 
 * @since: 2015年12月12日 下午5:01:32 
 * @history:
 */
public class XN702710 extends AProcessor {
    private IHLOrderAO hlOrderAO = SpringContextHolder
        .getBean(IHLOrderAO.class);

    private XN702710Req req = null;

    /** 
     * @see com.std.account.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        HLOrder condition = new HLOrder();

        condition.setHlNo(req.getHlNo());
        condition.setStatus(req.getStatus());
        condition.setDirection(req.getDirection());
        condition.setApplyUser(req.getApplyUser());
        condition.setApproveUser(req.getApproveUser());

        condition.setAccountNumber(req.getAccountNumber());
        condition.setMobile(req.getMobile());
        condition.setRealName(req.getRealName());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IHLOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return hlOrderAO.queryHLOrderList(condition);
    }

    /** 
     * @see com.std.account.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702710Req.class);
    }

}
