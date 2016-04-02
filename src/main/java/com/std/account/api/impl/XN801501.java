package com.std.account.api.impl;

import com.std.account.ao.ICompanyAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Company;
import com.std.account.dto.req.XN801501Req;
import com.std.account.dto.res.XN801501Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 获取公司详情
 * @author: xieyj 
 * @since: 2016年3月31日 下午12:33:14 
 * @history:
 */
public class XN801501 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN801501Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        XN801501Res res = new XN801501Res();
        Company company = companyAO.doGetCompany(req.getCompanyId());
        if (company != null) {
            Company data = new Company();
            res.setCompanyId(company.getCompanyId());
            res.setCompanyName(company.getCompanyName());
            res.setLicenceNo(company.getLicenceNo());
            res.setIdKind(company.getIdKind());
            res.setIdNo(company.getIdNo());

            res.setRealName(company.getRealName());
            res.setCurrency(company.getCurrency());
            res.setCapital(company.getCapital());
            res.setProvince(company.getProvince());
            res.setCity(company.getCity());
            res.setAddress(company.getAddress());

            res.setGsyyzzPicture(company.getGsyyzzPicture());
            res.setZzjgdmzPicture(company.getZzjgdmzPicture());
            res.setSwdjzPicture(company.getSwdjzPicture());
            res.setDzzPicture(company.getDzzPicture());
            res.setSqghPicture(company.getSqghPicture());

            res.setFrPicture(company.getFrPicture());
            res.setOtherPicture(company.getOtherPicture());

            res.setApplyUser(company.getApplyUser());
            res.setApplyDatetime(company.getApplyDatetime());
            res.setKycDatetime(company.getKycDatetime());
            res.setKycNote(company.getKycNote());
            res.setStatus(company.getStatus());

            res.setRemark(company.getRemark());
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801501Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getCompanyId());

    }

}
