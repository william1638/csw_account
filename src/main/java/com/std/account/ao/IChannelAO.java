/**
 * @Title IChannelAO.java 
 * @Package com.ibis.account.ao 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午4:34:45 
 * @version V1.0   
 */
package com.std.account.ao;

import java.util.List;

import com.std.account.annotation.ServiceModule;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Channel;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午4:34:45 
 * @history:
 */
@ServiceModule
public interface IChannelAO {
    String DEFAULT_ORDER_COLUMN = "channel_no";

    /** 
     * 新增组织
     * @param data
     * @return 
     * @create: 2015-5-19 下午3:44:46 miyb
     * @history: 
     */
    public String addChannel(Channel data);

    /**
     * 删除组织
     * @param channelNo 
     * @create: 2015年3月14日 下午3:32:21 茜茜
     * @history:
     */
    public boolean dropChannel(String channelNo);

    /** 
     * 更新组织信息
     * @param data
     * @return 
     * @create: 2015-5-19 下午4:18:50 miyb
     * @history: 
     */
    public boolean editChannel(Channel data);

    /** 
     * 按条件分页查询组织
     * @param start
     * @param limit
     * @param condition
     * @return 
     * @create: 2015-5-19 下午3:27:39 miyb
     * @history: 
     */
    public Paginable<Channel> queryChannelPage(int start, int limit,
            Channel condition);

    /** 
     * @param condition
     * @return 
     * @create: 2015-5-19 下午3:33:44 miyb
     * @history: 
     */
    public List<Channel> queryChannelList(Channel condition);
}
