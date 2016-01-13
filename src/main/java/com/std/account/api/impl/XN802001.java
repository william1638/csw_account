package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IChannelAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Channel;
import com.std.account.dto.req.XN702451Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 通道-分页查询
 * @author: myb858 
 * @since: 2015年9月18日 下午2:16:21 
 * @history:
 */
public class XN802001 extends AProcessor {
    private IChannelAO channelAO = SpringContextHolder
        .getBean(IChannelAO.class);

    private XN702451Req xn702451Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Channel condition = new Channel();
        condition.setChannelNo(xn702451Req.getChannelNo());
        condition.setChannelStatus(xn702451Req.getChannelStatus());

        String column = xn702451Req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IChannelAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, xn702451Req.getOrderDir());
        int start = Integer.valueOf(xn702451Req.getStart());
        int limit = Integer.valueOf(xn702451Req.getLimit());
        return channelAO.queryChannelPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702451Req = JsonUtil.json2Bean(inputparams, XN702451Req.class);
        StringValidater.validateNumber(xn702451Req.getStart(),
            xn702451Req.getLimit());

    }

}
