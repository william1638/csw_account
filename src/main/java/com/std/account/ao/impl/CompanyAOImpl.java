package com.std.account.ao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.ICompanyAO;
import com.std.account.bo.IBankCardBO;
import com.std.account.bo.ICompanyBO;
import com.std.account.bo.IUserBO;
import com.std.account.bo.IUserCompanyBO;
import com.std.account.bo.base.Paginable;
import com.std.account.core.OrderNoGenerater;
import com.std.account.domain.BankCard;
import com.std.account.domain.Company;
import com.std.account.domain.User;
import com.std.account.enums.EBankCardType;
import com.std.account.enums.EUserKind;
import com.std.account.exception.BizException;

/** 
 * 企业列表
 * @author: wuql
 * @since: 2016年1月14日 下午8:43:33 
 * @history:
 */
@Service
public class CompanyAOImpl implements ICompanyAO {
    @Autowired
    IUserBO userBO;

    @Autowired
    ICompanyBO companyBO;

    @Autowired
    IUserCompanyBO userCompanyBO;

    @Autowired
    IBankCardBO bankCardBO;

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
        Company company = companyBO.getCompany(companyId);
        if (company != null) {
            List<BankCard> bankCardList = bankCardBO.queryBankCardList(
                companyId, EBankCardType.Company);
            company.setBankCardList(bankCardList);
        }
        return company;
    }

    @Override
    public String addCompany(String companyName, String licenceNo,
            String idKind, String idNo, String realName, String currency,
            Long capital, String province, String city, String applyUser,
            String address) {
        User user = userBO.getUser(applyUser);
        if (user == null) {
            throw new BizException("xn000001", "提交申请人不存在");
        }
        if (!EUserKind.Admin.getCode().equalsIgnoreCase(user.getUserKind())) {
            throw new BizException("xn000001", "当前用户不是admin,不能KYC");
        }
        // 判断该用户是否已存在营业执照号
        judgeLicenceNoExist(applyUser, null, licenceNo);

        String companyId = OrderNoGenerater.generate("C");
        userCompanyBO.saveUserCompany(applyUser, companyId, "admin申请KYC");
        return companyBO.saveCompany(companyId, companyName, licenceNo, idKind,
            idNo, realName, currency, capital, province, city, applyUser,
            address);
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
            String currency, Long capital, String province, String city,
            String applyUser, String address) {
        Company company = companyBO.getCompany(companyId);
        // 判断该用户是否已存在营业执照号
        judgeLicenceNoExist(applyUser, companyId, licenceNo);
        companyBO.refreshCompany(companyId, companyName, licenceNo, idKind,
            idNo, realName, currency, capital, province, city, applyUser,
            address, company.getStatus());

    }

    // 判断该用户是否已存在营业执照号
    private void judgeLicenceNoExist(String userId, String companyId,
            String licenceNo) {
        List<Company> companyList = userCompanyBO.queryCompanyList(userId);
        if (CollectionUtils.isNotEmpty(companyList)) {
            if (StringUtils.isBlank(companyId)) {
                for (Company domain : companyList) {
                    if (domain.getLicenceNo().equals(licenceNo)) {
                        throw new BizException("xn000001", "营业执照号已存在");
                    }
                }
            } else {
                for (Company domain : companyList) {
                    if (!domain.getCompanyId().equals(companyId)
                            && domain.getLicenceNo().equals(licenceNo)) {
                        throw new BizException("xn000001", "营业执照号已存在");
                    }
                }
            }

        }

    }
}
