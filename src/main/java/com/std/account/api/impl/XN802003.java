package com.std.account.api.impl;

import com.std.account.ao.IChannelAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN702453Req;
import com.std.account.dto.res.XN702453Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 通道-删除
 * @author: myb858 
 * @since: 2015年9月18日 下午2:24:42 
 * @history:
 */
public class XN802003 extends AProcessor {
    private IChannelAO channelAO = SpringContextHolder
        .getBean(IChannelAO.class);

    private XN702453Req xn702453Req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN702453Res(
            channelAO.dropChannel(xn702453Req.getChannelNo()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702453Req = JsonUtil.json2Bean(inputparams, XN702453Req.class);
        StringValidater.validateBlank(xn702453Req.getChannelNo());

    }

}
