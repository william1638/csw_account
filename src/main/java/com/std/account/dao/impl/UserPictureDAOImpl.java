package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IUserPictureDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.UserPicture;

/**
 * @author: luoqi 
 * @since: 2015年3月7日 下午6:43:56 
 * @history:
 */
@Repository("userPictureDAOImpl")
public class UserPictureDAOImpl extends AMybatisTemplate implements
        IUserPictureDAO {

    /**
     * @see com.ibis.pz.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserPicture data) {
        return super.insert(NAMESPACE.concat("insert_userPicture"), data);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserPicture data) {
        return 0;
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserPicture select(UserPicture condition) {
        return super.select(NAMESPACE.concat("select_userPicture"), condition,
            UserPicture.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(UserPicture condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_userPicture_count"), condition);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserPicture> selectList(UserPicture condition) {
        return super.selectList(NAMESPACE.concat("select_userPicture"),
            condition, UserPicture.class);
    }

    /**
     * @see com.ibis.pz.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserPicture> selectList(UserPicture condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_userPicture"), start,
            count, condition, UserPicture.class);
    }

    @Override
    public int updateVerifyUserPicture(UserPicture data) {
        return super.update(NAMESPACE.concat("update_VerifyUserPicture"), data);
    }

}
