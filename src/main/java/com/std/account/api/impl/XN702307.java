package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IUserExtAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.UserExt;
import com.std.account.dto.req.XN702307Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 分页获取用户拓展资料
 * @author: myb858 
 * @since: 2015年10月29日 下午2:49:47 
 * @history:
 */
public class XN702307 extends AProcessor {
    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN702307Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        UserExt condition = new UserExt();
        condition.setUserId(req.getUserId());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserExtAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return userExtAO.queryUserExtPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702307Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
