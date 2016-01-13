/**
 * @Title IChannelDAO.java 
 * @Package com.ibis.account.dao 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 上午10:40:47 
 * @version V1.0   
 */
package com.std.account.dao;

import com.std.account.dao.base.IBaseDAO;
import com.std.account.domain.Channel;

/** 
 * @author: miyb 
 * @since: 2015-6-16 上午10:40:47 
 * @history:
 */
public interface IChannelDAO extends IBaseDAO<Channel> {
    String NAMESPACE = IChannelDAO.class.getName().concat(".");

    int update(Channel data);
}
