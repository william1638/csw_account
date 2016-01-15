package com.std.account.domain;

import com.std.account.dao.base.ABaseDO;

/**
 * 用户和公司关系表
 * @author: myb858 
 * @since: 2016年1月13日 下午10:18:36 
 * @history:
 */
public class UserCompany extends ABaseDO {
    private static final long serialVersionUID = 1975331351390818527L;

    // id
    private Long id;

    // 用户ID
    private String userId;

    // 公司ID
    private String companyId;

    // 备注
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
