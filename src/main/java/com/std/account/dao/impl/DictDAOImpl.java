package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IDictDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.Dict;

/**
 * 数据字典
 * @author: xieyj 
 * @since: 2015年9月10日 上午8:40:37 
 * @history:
 */
@Repository("dictDAOImpl")
public class DictDAOImpl extends AMybatisTemplate implements IDictDAO {

    /** 
     * @see com.xnjr.cpzc.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Dict data) {
        return super.insert("insert_dict", data);
    }

    /** 
     * @see com.xnjr.cpzc.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Dict data) {
        return super.delete("delete_dict", data);
    }

    /** 
     * @see com.xnjr.cpzc.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Dict select(Dict condition) {
        return super.select("select_dict", condition, Dict.class);
    }

    /** 
     * @see com.xnjr.cpzc.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(Dict condition) {
        return super.selectTotalCount("select_dict_count", condition);
    }

    /** 
     * @see com.xnjr.cpzc.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Dict> selectList(Dict condition) {
        return super.selectList("select_dict", condition, Dict.class);
    }

    /** 
     * @see com.xnjr.cpzc.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Dict> selectList(Dict condition, int start, int count) {
        return super.selectList("select_dict", start, count, condition,
            Dict.class);
    }

    /** 
     * @see com.xnjr.cpzc.IDictDAO#update(com.xnjr.cpzc.domain.Dict)
     */
    @Override
    public int update(Dict data) {
        return super.update("update_dict", data);
    }
}
