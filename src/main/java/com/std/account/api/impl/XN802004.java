package com.std.account.api.impl;

import com.std.account.ao.IChannelAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Channel;
import com.std.account.dto.req.XN702454Req;
import com.std.account.dto.res.XN702454Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 通道-修改
 * @author: myb858 
 * @since: 2015年9月18日 下午2:24:55 
 * @history:
 */
public class XN802004 extends AProcessor {
    private IChannelAO channelAO = SpringContextHolder
        .getBean(IChannelAO.class);

    private XN702454Req xn702454Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Channel data = new Channel();
        data.setChannelNo(xn702454Req.getChannelNo());
        data.setChannelName(xn702454Req.getChannelName());
        data.setChannelStatus(xn702454Req.getChannelStatus());
        data.setRemark(xn702454Req.getRemark());

        data.setMerchantId(xn702454Req.getMerchantId());
        data.setSignKey(xn702454Req.getSignKey());
        data.setSignType(xn702454Req.getSignType());
        data.setCerPath(xn702454Req.getCerPath());
        data.setPoundageType(xn702454Req.getPoundageType());
        data.setChannelVersion(xn702454Req.getChannelVersion());

        data.setBusinessFileGateway(xn702454Req.getBusinessFileGateway());
        data.setBusinessWapGateway(xn702454Req.getBusinessWapGateway());
        data.setBusinessWebGateway(xn702454Req.getBusinessWebGateway());

        return new XN702454Res(channelAO.editChannel(data));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702454Req = JsonUtil.json2Bean(inputparams, XN702454Req.class);
        StringValidater.validateBlank(xn702454Req.getChannelNo(),
            xn702454Req.getChannelName(), xn702454Req.getChannelStatus());

    }

}
