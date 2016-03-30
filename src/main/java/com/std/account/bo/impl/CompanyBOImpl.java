package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.ICompanyBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.ICompanyDAO;
import com.std.account.domain.Company;
import com.std.account.enums.EBoolean;
import com.std.account.enums.ECompanyStatus;
import com.std.account.exception.BizException;

/** 
 * 公司列表
 * @author: wuql
 * @since: 2016年1月14日 下午2:42:31 
 * @history:
 */
@Component
public class CompanyBOImpl extends PaginableBOImpl<Company> implements
        ICompanyBO {
    @Autowired
    private ICompanyDAO companyDAO;

    @Override
    public boolean isCompanyExist(String companyId) {
        Company company = new Company();
        company.setCompanyId(companyId);
        if (companyDAO.selectTotalCount(company) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Company getCompany(String companyId) {
        Company condition = new Company();
        Company companyDO = null;
        if (companyId != null) {
            condition.setCompanyId(companyId);
            companyDO = companyDAO.select(condition);
        }
        if (companyDO != null) {
            return companyDO;
        }
        throw new BizException("xn000001", "公司信息不存在！");
    }

    @Override
    public List<Company> queryCompanyList(Company condition) {
        return companyDAO.selectList(condition);
    }

    @Override
    public String saveCompany(String companyId, String companyName,
            String licenceNo, String idKind, String idNo, String realName,
            String currency, Long capital, String province, String city,
            String applyUser, String address) {
        Company data = new Company();
        data.setCompanyId(companyId);
        data.setCompanyName(companyName);
        data.setLicenceNo(licenceNo);
        data.setIdKind(idKind);
        data.setIdNo(idNo);

        data.setRealName(realName);
        data.setCurrency(currency);
        data.setCapital(capital);
        data.setProvince(province);
        data.setCity(city);
        data.setAddress(address);

        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setStatus(ECompanyStatus.DRAFT.getCode());
        data.setRemark("添加基本信息");
        companyDAO.insert(data);
        return companyId;
    }

    @Override
    public int refreshCompany(String companyId, String companyName,
            String licenceNo, String idKind, String idNo, String realName,
            String currency, Long capital, String province, String city,
            String applyUser, String address, String status) {
        int count = 0;
        if (StringUtils.isNotBlank(companyId)) {
            Company data = new Company();
            data.setCompanyId(companyId);
            data.setCompanyName(companyName);
            data.setLicenceNo(licenceNo);
            data.setIdKind(idKind);
            data.setIdNo(idNo);

            data.setRealName(realName);
            data.setCurrency(currency);
            data.setCapital(capital);
            data.setProvince(province);
            data.setCity(city);
            data.setAddress(address);

            data.setApplyUser(applyUser);
            data.setApplyDatetime(new Date());
            data.setStatus(status);
            data.setRemark("修改基本信息");
            count = companyDAO.updateCompany(data);
        }
        return count;
    }

    @Override
    public int refreshPicture(String companyId, String gsyyzzPicture,
            String zzjgdmzPicture, String swdjzPicture, String dzzPicture,
            String sqghPicture, String frPicture, String otherPicture) {
        int count = 0;
        if (StringUtils.isNotBlank(companyId)) {
            Company data = new Company();
            data.setCompanyId(companyId);
            data.setGsyyzzPicture(gsyyzzPicture);
            data.setZzjgdmzPicture(zzjgdmzPicture);
            data.setSwdjzPicture(swdjzPicture);
            data.setDzzPicture(dzzPicture);
            data.setSqghPicture(sqghPicture);

            data.setFrPicture(frPicture);
            data.setOtherPicture(otherPicture);
            data.setStatus(ECompanyStatus.todoKYC.getCode());
            count = companyDAO.updatePicture(data);
        }
        return count;
    }

    @Override
    public int doKYC(String companyId, String kycUser, String kycResult,
            String kycNote) {
        int count = 0;
        if (StringUtils.isNotBlank(companyId)) {
            Company data = new Company();
            data.setCompanyId(companyId);
            data.setKycUser(kycUser);
            data.setKycNote(kycNote);
            data.setKycDatetime(new Date());
            if (EBoolean.YES.getCode().equalsIgnoreCase(kycResult)) {
                data.setStatus(ECompanyStatus.KYC_YES.getCode());
            } else {
                data.setStatus(ECompanyStatus.KYC_NO.getCode());
            }
            count = companyDAO.doKYC(data);
        }
        return count;

    }

}
