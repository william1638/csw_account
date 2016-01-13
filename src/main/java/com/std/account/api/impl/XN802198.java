package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.ISmsCaptchaAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.SmsCaptcha;
import com.std.account.dto.req.XN702498Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 验证码分页查询
 * @author: myb858 
 * @since: 2015年11月10日 下午12:27:39 
 * @history:
 */
public class XN802198 extends AProcessor {
    private ISmsCaptchaAO smsCaptchaAO = SpringContextHolder
        .getBean(ISmsCaptchaAO.class);

    private XN702498Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SmsCaptcha condition = new SmsCaptcha();
        condition.setId(StringValidater.toLong(req.getId()));
        condition.setMobile(req.getMobile());
        condition.setBizType(req.getBizType());
        condition.setStatus(req.getStatus());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISmsCaptchaAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return smsCaptchaAO.querySmsCaptchaPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN702498Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
