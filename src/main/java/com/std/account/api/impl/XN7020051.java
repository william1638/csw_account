package com.std.account.api.impl;

import com.std.account.api.AProcessor;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.cms.ao.INavigationAO;
import com.std.account.cms.domain.Navigation;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020051Req;
import com.std.account.enums.ENavStatus;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 可使用导航列表查询
 * @author: xieyj 
 * @since: 2016年1月6日 上午9:14:16 
 * @history:
 */
public class XN7020051 extends AProcessor {
    private INavigationAO navigationAO = SpringContextHolder
        .getBean(INavigationAO.class);

    private XN7020051Req xn7020051Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Navigation condition = new Navigation();
        condition.setParentCode(xn7020051Req.getParentCode());
        condition.setStatus(ENavStatus.USERED.getCode());
        condition.setOrder(IArticleAO.DEFAULT_ORDER_COLUMN, "DESC");
        return navigationAO.queryNavigationList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020051Req = JsonUtil.json2Bean(inputparams, XN7020051Req.class);
        StringValidater.validateBlank(xn7020051Req.getParentCode());
    }
}
