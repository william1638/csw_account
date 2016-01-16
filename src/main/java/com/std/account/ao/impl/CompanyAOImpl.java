package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.ICompanyAO;
import com.std.account.bo.ICompanyBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Company;

/** 
 * 企业列表
 * @author: wuql
 * @since: 2016年1月14日 下午8:43:33 
 * @history:
 */
@Service
public class CompanyAOImpl implements ICompanyAO {
    @Autowired
    ICompanyBO companyBO;

    @Override
    public Paginable<Company> queryCompanyPage(int start, int limit,
            Company condition) {
        return companyBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyBO.queryCompanyList(condition);
    }

    @Override
    public Company doGetCompany(String companyId) {
        return companyBO.getCompany(companyId);
    }

    @Override
    public String addCompany(String companyName, String licenceNo,
            String idKind, String idNo, String realName, Long capital,
            String province, String city, String applyUser, String address) {
        return companyBO.saveCompany(companyName, licenceNo, idKind, idNo,
            realName, capital, province, city, applyUser, address);
    }

    @Override
    public void editPicture(String companyId, String gsyyzzPicture,
            String zzjgdmzPicture, String swdjzPicture, String dzzPicture,
            String sqghPicture, String frPicture, String otherPicture) {
        companyBO.refreshPicture(companyId, gsyyzzPicture, zzjgdmzPicture,
            swdjzPicture, dzzPicture, sqghPicture, frPicture, otherPicture);

    }

    @Override
    public void editCompany(String companyId, String companyName,
            String licenceNo, String idKind, String idNo, String realName,
            Long capital, String province, String city, String applyUser,
            String address) {
        companyBO.refreshCompany(companyId, companyName, licenceNo, idKind,
            idNo, realName, capital, province, city, applyUser, address);

    }
}
