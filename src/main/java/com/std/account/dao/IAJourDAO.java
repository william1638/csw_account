/**
 * @Title IAccountJour.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-2-14 下午2:08:28 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.Jour;

/** 
 * @author: miyb 
 * @since: 2015-2-14 下午2:08:28 
 * @history:
 */
public interface IAJourDAO extends IBaseDAO<Jour> {
    String NAMESPACE = IAJourDAO.class.getName().concat(".");

    /**
     * 金额发生变动
     * @param data
     * @return 
     * @create: 2016年11月10日 下午6:05:41 xieyj
     * @history:
     */
    public int updateTrans(Jour data);

    /**
     * 对账结果录入
     * @param data
     * @return 
     * @create: 2016年11月10日 下午6:05:41 xieyj
     * @history:
     */
    public int updateCheck(Jour data);

    /**
     * 调账结果录入
     * @param data
     * @return 
     * @create: 2016年11月10日 下午6:05:47 xieyj
     * @history:
     */
    public int updateAdjust(Jour data);
}
