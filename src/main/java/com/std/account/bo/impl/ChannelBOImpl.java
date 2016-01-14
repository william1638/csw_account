/**
 * @Title ChannelBOImpl.java 
 * @Package com.ibis.account.bo.impl 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午4:53:16 
 * @version V1.0   
 */
package com.std.account.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.std.account.bo.IChannelBO;
import com.std.account.bo.base.PaginableBOImpl;
import com.std.account.dao.IChannelDAO;
import com.std.account.domain.Channel;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午4:53:16 
 * @history:
 */
@Component
public class ChannelBOImpl extends PaginableBOImpl<Channel> implements
        IChannelBO {
    @Autowired
    private IChannelDAO channelDAO;

    /** 
     * @see com.ibis.account.bo.IChannelBO#saveChannel(com.ibis.account.domain.Channel)
     */
    @Override
    public int saveChannel(Channel data) {
        int count = 0;
        if (data != null) {
            count = channelDAO.insert(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.account.bo.IChannelBO#removeChannel(java.lang.String)
     */
    @Override
    public int removeChannel(String channelNo) {
        int count = 0;
        if (StringUtils.isNotBlank(channelNo)) {
            Channel data = new Channel();
            data.setChannelNo(channelNo);
            count = channelDAO.delete(data);
        }
        return count;
    }

    /** 
     * @see com.ibis.account.bo.IChannelBO#refreshChannel(com.ibis.account.domain.Channel)
     */
    @Override
    public int refreshChannel(Channel data) {
        return channelDAO.update(data);
    }

    /** 
     * @see com.ibis.account.bo.IChannelBO#getChannel(java.lang.String)
     */
    @Override
    public Channel getChannel(String channelNo) {
        Channel data = null;
        if (StringUtils.isNotBlank(channelNo)) {
            Channel condition = new Channel();
            condition.setChannelNo(channelNo);
            data = channelDAO.select(condition);
        }
        return data;
    }

    /** 
     * @see com.ibis.account.bo.IChannelBO#queryChannelList(com.ibis.account.domain.Channel)
     */
    @Override
    public List<Channel> queryChannelList(Channel data) {
        return channelDAO.selectList(data);
    }

    @Override
    public boolean isChannelExist(String channelNo) {
        Channel channel = new Channel();
        channel.setChannelNo(channelNo);
        if (channelDAO.selectTotalCount(channel) == 1) {
            return true;
        }
        return false;
    }

}
