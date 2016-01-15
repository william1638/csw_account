package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.ao.IChannelAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.Channel;
import com.std.account.dto.req.XN802001Req;
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

    private XN802001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Channel condition = new Channel();
        condition.setChannelNo(req.getChannelNo());
        condition.setChannelStatus(req.getChannelStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IChannelAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return channelAO.queryChannelPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802001Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());

    }

}
