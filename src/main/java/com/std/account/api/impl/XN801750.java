package com.std.account.api.impl;

import com.std.account.ao.ICompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Company;
import com.std.account.dto.req.XN801750Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

import antlr.StringUtils;

/**
 * 分页查询企业列表
 * @author: myb858 
 * @since: 2016年1月14日 下午3:42:41 
 * @history:
 */
public class XN801750 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN801750Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Company condition = new Company();
        condition.setCompanyId(req.getCompanyId());
        condition.setCompanyName(req.getCompanyName());
        condition.setLicenceNo(req.getLicenceNo());
        condition.setIdKind(req.getIdKind());
        condition.setIdNo(req.getIdNo());
        condition.setRealName(req.getRealName());
        condition.setStatus(req.getStatus());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = ICompanyAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return companyAO.queryCompanyPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801750Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
    }

}
