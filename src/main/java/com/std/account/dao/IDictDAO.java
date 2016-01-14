package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.Dict;

/**
 * 数据字典DAO
 * @author: xieyj 
 * @since: 2015年9月12日 上午10:08:26 
 * @history:
 */
public interface IDictDAO extends IBaseDAO<Dict> {
    String NAMESPACE = IDictDAO.class.getName().concat(".");

    public int update(Dict data);
}
