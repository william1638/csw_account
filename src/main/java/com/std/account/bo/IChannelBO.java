/**
 * @Title IChannelBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午4:51:43 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Channel;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午4:51:43 
 * @history:
 */
public interface IChannelBO extends IPaginableBO<Channel> {
    public boolean isChannelExist(String channelNo);

    public int saveChannel(Channel data);

    public int removeChannel(String channelNo);

    public int refreshChannel(Channel data);

    public Channel getChannel(String channelNo);

    public List<Channel> queryChannelList(Channel data);
}
