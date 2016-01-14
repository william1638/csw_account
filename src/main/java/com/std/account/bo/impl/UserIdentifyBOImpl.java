/**
 * @Title UserIdentifyBOImpl.java 
 * @Package com.ibis.pz.user.impl 
 * @Description 
 * @author luoqi  
 * @date 2015年3月8日 上午11:32:00 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IUserIdentifyBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.common.DateUtil;
import com.std.account.dao.IUserIdentifyDAO;
import com.std.account.domain.UserIdentify;
import com.std.account.exception.BizException;

/** 
 * @author: luoqi 
 * @since: 2015年3月8日 上午11:32:00 
 * @history:
 */
@Component
public class UserIdentifyBOImpl extends PaginableBOImpl<UserIdentify> implements
        IUserIdentifyBO {
    @Autowired
    private IUserIdentifyDAO userIdentifyDAO;

    /** 
     * @see com.ibis.pz.user.IUserIdentifyBO#saveUserIdentify(com.UserIdentify.pz.domain.UserIdentifyDO)
     */
    @Override
    public int saveUserIdentify(String userId, String realName, String idKind,
            String idcard, String errorCode, String errorInfo) {
        UserIdentify data = new UserIdentify();
        data.setUserId(userId);
        data.setRealName(realName);
        data.setIdKind(idKind);
        data.setIdNo(idcard);
        data.setErrorCode(errorCode);
        data.setErrorInfo(errorInfo);
        data.setCreateDatetime(new Date());
        int count = 0;
        if (data != null) {
            count = userIdentifyDAO.insert(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.pz.user.IUserIdentifyBO#queryUserIdentifyList(java.lang.String)
     */
    @Override
    public List<UserIdentify> queryUserIdentifyList(String userId) {
        List<UserIdentify> list = null;
        if (StringUtils.isNotBlank(userId)) {
            UserIdentify condition = new UserIdentify();
            condition.setUserId(userId);
            list = userIdentifyDAO.selectList(condition);
        }
        return list;
    }

    /** 
     * @see com.ibis.pz.user.IUserIdentifyBO#queryTodayIdentifyList(java.lang.String)
     */
    @Override
    public List<UserIdentify> queryTodayIdentifyList(String userId) {
        List<UserIdentify> list = null;
        if (StringUtils.isNotBlank(userId)) {
            UserIdentify condition = new UserIdentify();
            condition.setUserId(userId);
            condition.setCreateDatetimeStart(DateUtil.getTodayStart());
            list = userIdentifyDAO.selectList(condition);
        }
        return list;
    }

    /** 
     * @see com.ibis.pz.user.IUserIdentifyBO#queryUserIdentifyList(com.UserIdentify.pz.domain.UserIdentifyDO)
     */
    @Override
    public List<UserIdentify> queryUserIdentifyList(UserIdentify data) {
        return userIdentifyDAO.selectList(data);
    }

    @Override
    public void isChecked(String realName, String idKind, String idNo) {
        UserIdentify userIdentify = this.getSuccessOne(realName, idKind, idNo);
        if (userIdentify != null) {
            throw new BizException("li01004", "该证件信息已经被认证过，不能二次认证");
        }
    }

    @Override
    public UserIdentify getSuccessOne(String realName, String idKind,
            String idNo) {
        UserIdentify data = null;
        if (StringUtils.isNotBlank(realName) && StringUtils.isNotBlank(idKind)
                && StringUtils.isNotBlank(idNo)) {
            UserIdentify condition = new UserIdentify();
            condition.setRealName(realName);
            condition.setIdKind(idKind);
            condition.setIdNo(idNo);
            condition.setErrorCode("0");
            List<UserIdentify> list = userIdentifyDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

}
