/**
 * @Title ChannelAOImpl.java 
 * @Package com.ibis.account.ao.impl 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午4:35:00 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IChannelAO;
import com.std.account.bo.IChannelBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Channel;
import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午4:35:00 
 * @history:
 */
@Service
public class ChannelAOImpl implements IChannelAO {
    @Autowired
    IChannelBO channelBO;

    /** 
     * @see com.ibis.account.ao.IChannelAO#addChannel(com.ibis.account.domain.Channel)
     */
    @Override
    public String addChannel(Channel data) {
        if (data != null && !channelBO.isChannelExist(data.getChannelNo())) {
            channelBO.saveChannel(data);
        } else {
            throw new BizException("li01006", "通道编号已经存在！");
        }
        return data.getChannelNo();
    }

    /** 
     * @see com.ibis.account.ao.IChannelAO#dropChannel(java.lang.String)
     */
    @Override
    public boolean dropChannel(String channelNo) {
        if (!channelBO.isChannelExist((channelNo))) {
            throw new BizException("xn702000", "删除通道不存在！");
        }
        channelBO.removeChannel(channelNo);
        return true;
    }

    /** 
     * @see com.ibis.account.ao.IChannelAO#editChannel(com.ibis.account.domain.Channel)
     */
    @Override
    public boolean editChannel(Channel data) {
        if (data != null && channelBO.isChannelExist(data.getChannelNo())) {
            channelBO.refreshChannel(data);
        } else {
            throw new BizException("li01007", "支付通道编号不存在！");
        }
        return true;
    }

    /** 
     * @see com.ibis.account.ao.IChannelAO#queryChannelPage(int, int, com.ibis.account.domain.Channel)
     */
    @Override
    public Paginable<Channel> queryChannelPage(int start, int limit,
            Channel condition) {
        return channelBO.getPaginable(start, limit, condition);
    }

    /** 
     * @see com.ibis.account.ao.IChannelAO#queryChannelList(com.ibis.account.domain.Channel)
     */
    @Override
    public List<Channel> queryChannelList(Channel condition) {
        return channelBO.queryChannelList(condition);
    }

}
