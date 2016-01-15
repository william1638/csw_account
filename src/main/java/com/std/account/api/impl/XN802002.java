package com.std.account.api.impl;

import com.std.account.ao.IChannelAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Channel;
import com.std.account.dto.req.XN802002Req;
import com.std.account.dto.res.XN802002Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 通道-增加
 * @author: myb858 
 * @since: 2015年9月18日 下午2:24:08 
 * @history:
 */
public class XN802002 extends AProcessor {
    private IChannelAO channelAO = SpringContextHolder
        .getBean(IChannelAO.class);

    private XN802002Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Channel data = new Channel();
        data.setChannelNo(req.getChannelNo());
        data.setChannelName(req.getChannelName());
        data.setChannelStatus(req.getChannelStatus());
        data.setRemark(req.getRemark());

        return new XN802002Res(channelAO.addChannel(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802002Req.class);
        StringValidater.validateBlank(req.getChannelNo(), req.getChannelName(),
            req.getChannelStatus());

    }

}
