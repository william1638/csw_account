/**
 * @Title XN702709.java 
 * @Package com.xnjr.account.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年12月12日 下午5:01:13 
 * @version V1.0   
 */
package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.ICQOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.domain.CQOrder;
import com.std.account.dto.req.XN702709Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/** 
 * 充值提现记录查询
 * @author: haiqingzheng 
 * @since: 2015年12月12日 下午5:01:13 
 * @history:
 */
public class XN702709 extends AProcessor {
    private ICQOrderAO cqOrderAO = SpringContextHolder
        .getBean(ICQOrderAO.class);

    private XN702709Req req = null;

    /** 
     * @see com.std.account.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        CQOrder condition = new CQOrder();

        condition.setCqNo(req.getCqNo());
        condition.setStatus(req.getStatus());
        condition.setDirection(req.getDirection());
        condition.setChannel(req.getChannel());

        condition.setBankCode(req.getBankCode());
        condition.setApproveUser(req.getApproveUser());
        condition.setPayUser(req.getPayUser());
        condition.setCheckUser(req.getCheckUser());
        condition.setWorkDate(DateUtil.remove_(req.getWorkDate()));
        condition.setAccountNumber(req.getAccountNumber());
        condition.setMobile(req.getMobile());
        condition.setRealName(req.getRealName());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICQOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        return cqOrderAO.queryCQOrderList(condition);
    }

    /** 
     * @see com.std.account.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702709Req.class);
    }

}
