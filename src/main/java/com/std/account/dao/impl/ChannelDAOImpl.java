/**
 * @Title ChannelDAOImpl.java 
 * @Package com.ibis.account.dao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 上午10:45:00 
 * @version V1.0   
 */
package com.std.account.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.std.account.dao.IChannelDAO;
import com.std.account.dao.base.support.AMybatisTemplate;
import com.std.account.domain.Channel;

/** 
 * @author: miyb 
 * @since: 2015-6-16 上午10:45:00 
 * @history:
 */
@Repository("channelDAOImpl")
public class ChannelDAOImpl extends AMybatisTemplate implements IChannelDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Channel data) {
        return super.insert(NAMESPACE.concat("insert_channel"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Channel data) {
        return super.delete(NAMESPACE.concat("delete_channel"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Channel select(Channel condition) {
        return (Channel) super.select(NAMESPACE.concat("select_channel"),
            condition, Channel.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public long selectTotalCount(Channel condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_channel_count"),
            condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Channel> selectList(Channel condition) {
        return super.selectList(NAMESPACE.concat("select_channel"), condition,
            Channel.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Channel> selectList(Channel condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_channel"), start,
            count, condition, Channel.class);
    }

    @Override
    public int update(Channel data) {
        return super.update(NAMESPACE.concat("update_channel"), data);
    }

}
