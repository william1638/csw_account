package com.std.account.api.impl;

import com.std.account.ao.IChannelAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Channel;
import com.std.account.dto.req.XN702452Req;
import com.std.account.dto.res.XN702452Res;
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

    private XN702452Req xn702452Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Channel data = new Channel();
        data.setChannelNo(xn702452Req.getChannelNo());
        data.setChannelName(xn702452Req.getChannelName());
        data.setChannelStatus(xn702452Req.getChannelStatus());
        data.setRemark(xn702452Req.getRemark());

        data.setMerchantId(xn702452Req.getMerchantId());
        data.setSignKey(xn702452Req.getSignKey());
        data.setSignType(xn702452Req.getSignType());
        data.setCerPath(xn702452Req.getCerPath());
        data.setPoundageType(xn702452Req.getPoundageType());
        data.setChannelVersion(xn702452Req.getChannelVersion());

        data.setBusinessFileGateway(xn702452Req.getBusinessFileGateway());
        data.setBusinessWapGateway(xn702452Req.getBusinessWapGateway());
        data.setBusinessWebGateway(xn702452Req.getBusinessWebGateway());

        return new XN702452Res(channelAO.addChannel(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702452Req = JsonUtil.json2Bean(inputparams, XN702452Req.class);
        StringValidater.validateBlank(xn702452Req.getChannelNo(),
            xn702452Req.getChannelName(), xn702452Req.getChannelStatus());

    }

}
