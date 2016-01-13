package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.domain.User;
import com.std.account.dto.req.XN702301Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 查询用户列表
 * @author: myb858 
 * @since: 2015年10月27日 下午4:03:21 
 * @history:
 */
public class XN702301 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN702301Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        User condition = new User();
        condition.setUserId(req.getUserId());
        condition.setMobile(req.getMobile());
        condition.setUserKind(req.getUserKind());
        condition.setUserReferee(req.getUserReferee());
        condition.setIdKind(req.getIdKind());
        condition.setIdNo(req.getIdNo());
        condition.setRealName(req.getRealName());
        condition.setStatus(req.getStatus());
        return userAO.queryUserList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702301Req.class);
    }

}
