package com.std.account.api.impl;

import com.std.account.ao.IChannelAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.domain.Channel;
import com.std.account.dto.req.XN702450Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 通道-列表查询
 * @author: myb858 
 * @since: 2015年9月18日 下午2:03:09 
 * @history:
 */
public class XN802000 extends AProcessor {
    private IChannelAO channelAO = SpringContextHolder
        .getBean(IChannelAO.class);

    private XN702450Req xn702450Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Channel condition = new Channel();
        condition.setChannelNo(xn702450Req.getChannelNo());
        condition.setChannelStatus(xn702450Req.getChannelStatus());
        return channelAO.queryChannelList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702450Req = JsonUtil.json2Bean(inputparams, XN702450Req.class);

    }

}
