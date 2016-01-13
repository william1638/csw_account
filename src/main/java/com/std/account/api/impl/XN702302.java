package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.DateUtil;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.UserLoginLog;
import com.std.account.dto.req.XN702302Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 根据UserId分页获取登录日志
 * @author: myb858 
 * @since: 2015年10月27日 下午4:05:37 
 * @history:
 */
public class XN702302 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702302Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        UserLoginLog condition = new UserLoginLog();
        condition.setUserId(req.getUserId());
        condition.setIsSuccess(req.getIsSuccess());
        condition.setLoginIp(req.getLoginIp());
        condition.setLoginDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setLoginDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return userAO.queryUserLoginLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702302Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getUserId());
    }

}
