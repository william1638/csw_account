package com.std.account.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.ICompanyChannelBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.ICompanyChannelDAO;
import com.std.account.domain.CompanyChannel;
import com.std.account.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2016年11月10日 下午7:49:32 
 * @history:
 */
@Component
public class CompanyChannelBOImpl extends PaginableBOImpl<CompanyChannel>
        implements ICompanyChannelBO {

    @Autowired
    private ICompanyChannelDAO CompanyChannelDAO;

    @Override
    public boolean isCompanyChannelExist(Long id) {
        CompanyChannel condition = new CompanyChannel();
        condition.setId(id);
        if (CompanyChannelDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public long getCompanyChannelCount(String companyCode, String channelType) {
        CompanyChannel condition = new CompanyChannel();
        condition.setCompanyCode(companyCode);
        condition.setChannelType(channelType);
        return CompanyChannelDAO.selectTotalCount(condition);
    }

    @Override
    public Long saveCompanyChannel(CompanyChannel data) {
        Long id = null;
        if (data != null) {
            CompanyChannelDAO.insert(data);
            id = data.getId();
        }
        return id;
    }

    @Override
    public int removeCompanyChannel(Long id) {
        int count = 0;
        if (id != null) {
            CompanyChannel data = new CompanyChannel();
            data.setId(id);
            count = CompanyChannelDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshCompanyChannel(CompanyChannel data) {
        int count = 0;
        if (data != null) {
            count = CompanyChannelDAO.update(data);
        }
        return count;
    }

    @Override
    public List<CompanyChannel> queryCompanyChannelList(CompanyChannel condition) {
        return CompanyChannelDAO.selectList(condition);
    }

    @Override
    public CompanyChannel getCompanyChannel(Long id) {
        CompanyChannel data = null;
        if (id != null) {
            CompanyChannel condition = new CompanyChannel();
            condition.setId(id);
            data = CompanyChannelDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "公司渠道不存在");
            }
        }
        return data;
    }
}
