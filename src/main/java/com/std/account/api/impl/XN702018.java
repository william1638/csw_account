package com.std.account.api.impl;

import com.std.account.ao.IUserExtAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702018Req;
import com.std.account.dto.res.XN702018Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 辅助-完善个人信息
 * @author: myb858 
 * @since: 2015年9月15日 下午2:36:49 
 * @history:
 */
public class XN702018 extends AProcessor {
    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN702018Req xn702018Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702018Res(userExtAO.doRichUserExtInfo(
            xn702018Req.getUserId(), xn702018Req.getComefrom(),
            xn702018Req.getBirthday(), xn702018Req.getQq(),
            xn702018Req.getEmail(), xn702018Req.getOccupation(),
            xn702018Req.getEducation()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702018Req = JsonUtil.json2Bean(inputparams, XN702018Req.class);
        StringValidater.validateBlank(xn702018Req.getUserId());
    }

}
