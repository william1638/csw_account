/**
 * @Title IDictAO.java 
 * @Package com.xnjr.cpzc.system 
 * @Description 
 * @author xieyj  
 * @date 2015年9月10日 上午8:50:17 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.List;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Dict;

/** 
 * @author: xieyj 
 * @since: 2015年9月10日 上午8:50:17 
 * @history:
 */
@ServiceModule
public interface IDictAO {
    String DEFAULT_ORDER_COLUMN = "id";

    /**
     * 新增数据字典
     * @param pId
     * @param key
     * @param value
     * @param creator
     * @param remark
     * @return 
     * @create: 2015年10月29日 下午4:09:34 myb858
     * @history:
     */
    public int addDict(Long pId, String key, String value, String creator,
            String remark);

    /**
     * 删除数据字典
     * @param id
     * @return 
     * @create: 2015年9月12日 下午2:24:19 xieyj
     * @history:
     */
    public int dropDict(Long id);

    /**
     * 修改数据字典
     * @param id
     * @param pId
     * @param key
     * @param value
     * @param updater
     * @param remark
     * @return 
     * @create: 2015年10月29日 下午4:10:17 myb858
     * @history:
     */
    public int editDict(Long id, Long pId, String key, String value,
            String updater, String remark);

    /**
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015年9月10日 上午8:56:42 xieyj
     * @history:
     */
    public Paginable<Dict> queryDictPage(int start, int limit, Dict condition);

    /**
     * @param key
     * @param value
     * @param pId
     * @param pKey
     * @param pValue
     * @return 
     * @create: 2015年9月10日 上午8:57:42 xieyj
     * @history:
     */
    public List<Dict> queryDictList(Dict condition);
}
