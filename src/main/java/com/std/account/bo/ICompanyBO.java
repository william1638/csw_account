/**
 * @Title ICompanyBO.java 
 * @Package com.std.account.bo
 * @Description 
 * @author wuql  
 * @date 2016年1月14日 下午2:38:13  
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Company;

/** 
 * @author: wuql
 * @since: 2016年1月14日 下午2:38:13 
 * @history:
 */
public interface ICompanyBO extends IPaginableBO<Company> {
    public boolean isCompanyExist(String companyId);

    public Company getCompany(String companyId);

    public List<Company> queryCompanyList(Company condition);

    public String saveCompany(String companyId, String companyName,
            String licenceNo, String idKind, String idNo, String realName,
            Long capital, String province, String city, String applyUser,
            String address);

    public int refreshPicture(String companyId, String gsyyzzPicture,
            String zzjgdmzPicture, String swdjzPicture, String dzzPicture,
            String sqghPicture, String frPicture, String otherPicture);

    public int refreshCompany(String companyId, String companyName,
            String licenceNo, String idKind, String idNo, String realName,
            Long capital, String province, String city, String applyUser,
            String address);

    public int doKYC(String companyId, String kycUser, String kycResult,
            String kycNote);
}
