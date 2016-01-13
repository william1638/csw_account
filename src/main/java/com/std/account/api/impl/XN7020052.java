package com.std.account.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.std.account.api.AProcessor;
import com.std.account.cms.ao.IArticleAO;
import com.std.account.cms.domain.Article;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN7020052Req;
import com.std.account.enums.ENavStatus;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 可使用文章分页查询
 * @author: xieyj 
 * @since: 2016年1月6日 上午9:14:16 
 * @history:
 */
public class XN7020052 extends AProcessor {
    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN7020052Req xn7020052Req = null;

    @Override
    public Object doBusiness() throws BizException {
        Article condition = new Article();
        condition.setParentCode(xn7020052Req.getParentCode());
        condition.setStatus(ENavStatus.USERED.getCode());

        String column = xn7020052Req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IArticleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, "DESC");
        return articleAO.queryArticlePage(
            Integer.valueOf(xn7020052Req.getStart()),
            Integer.valueOf(xn7020052Req.getLimit()), condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn7020052Req = JsonUtil.json2Bean(inputparams, XN7020052Req.class);
        StringValidater.validateBlank(xn7020052Req.getParentCode(),
            xn7020052Req.getStart(), xn7020052Req.getLimit());
        StringValidater.validateNumber(xn7020052Req.getStart(),
            xn7020052Req.getLimit());
    }
}
