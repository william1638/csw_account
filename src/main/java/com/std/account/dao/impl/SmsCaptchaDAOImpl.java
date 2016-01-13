package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.ISmsCaptchaDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.SmsCaptcha;

/**
 * @author: xieyj 
 * @since: 2015-3-7 下午3:20:16 
 * @history:
 */
@Repository("smsCaptchaDAOImpl")
public class SmsCaptchaDAOImpl extends AMybatisTemplate implements
        ISmsCaptchaDAO {

    /**
     * @see com.ibis.pz.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(SmsCaptcha data) {
        return super.insert(NAMESPACE.concat("insert_smsCaptcha"), data);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(SmsCaptcha data) {
        return super.update(NAMESPACE.concat("delete_smsCaptcha"), data);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public SmsCaptcha select(SmsCaptcha condition) {
        return (SmsCaptcha) super.select(NAMESPACE.concat("select_smsCaptcha"),
            condition, SmsCaptcha.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(SmsCaptcha condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_smsCaptcha_count"), condition);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<SmsCaptcha> selectList(SmsCaptcha condition) {
        return super.selectList(NAMESPACE.concat("select_smsCaptcha"),
            condition, SmsCaptcha.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<SmsCaptcha> selectList(SmsCaptcha condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_smsCaptcha"), start,
            count, condition, SmsCaptcha.class);
    }

    @Override
    public int updateCheckInfo(SmsCaptcha data) {
        return super.update(NAMESPACE.concat("update_checkInfo"), data);
    }

}
