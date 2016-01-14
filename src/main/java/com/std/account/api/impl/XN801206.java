package com.std.account.api.impl;

import com.std.account.ao.IUserExtAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801206Req;
import com.std.account.dto.res.XN801206Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 完善个人信息
 * @author: myb858 
 * @since: 2015年9月15日 下午2:36:49 
 * @history:
 */
public class XN801206 extends AProcessor {
    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN801206Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN801206Res(userExtAO.doRichUserExtInfo(req.getUserId(),
            req.getComefrom(), req.getBirthday(), req.getQq(), req.getEmail(),
            req.getOccupation(), req.getEducation()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801206Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
