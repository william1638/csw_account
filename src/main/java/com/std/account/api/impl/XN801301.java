package com.std.account.api.impl;

import com.std.account.ao.ICompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN801301Req;
import com.std.account.dto.res.XN801301Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 企业资料补充（修改）
 * @author: myb858 
 * @since: 2016年1月14日 下午3:32:32 
 * @history:
 */
public class XN801301 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN801301Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        companyAO.editPicture(req.getCompanyId(), req.getGsyyzzPicture(),
            req.getZzjgdmzPicture(), req.getSwdjzPicture(),
            req.getDzzPicture(), req.getSqghPicture(), req.getFrPicture(),
            req.getOtherPicture());
        return new XN801301Res(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801301Req.class);
        StringValidater.validateBlank(req.getCompanyId());
    }

}
