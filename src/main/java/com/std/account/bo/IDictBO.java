/**
 * @Title ISupportBO.java 
 * @Package com.xnjr.cpzc.support 
 * @Description 
 * @author xieyj  
 * @date 2015年8月20日 下午5:39:36 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Dict;

/** 
 * @author: xieyj 
 * @since: 2015年8月20日 下午5:39:36 
 * @history:
 */
public interface IDictBO extends IPaginableBO<Dict> {
    public boolean isDictExist(Long id);

    public int saveDict(Dict data);

    public int removeDict(Long id);

    public int refreshDict(Dict data);

    public Dict getDict(Long id);

    public List<Dict> queryDictListByPid(Long pId);

    public List<Dict> queryDictListByKey(String key);

    public List<Dict> queryDictList(Dict condition);
}
