package com.std.account.api.converter;

import org.apache.commons.lang3.StringUtils;

import com.std.account.cms.domain.Navigation;
import com.std.account.common.DateUtil;
import com.std.account.dto.req.XN7020101Req;
import com.std.account.dto.req.XN7020103Req;
import com.std.account.dto.req.XN7020201Req;
import com.std.account.dto.req.XN7020202Req;

public class NavigationConverter {
    // 增加
    public static Navigation convert(XN7020101Req xn7020001Req) {
        Navigation navigation = new Navigation();
        navigation.setCode(xn7020001Req.getCode());
        navigation.setTitle(xn7020001Req.getTitle());
        navigation.setNavUrl(xn7020001Req.getNavUrl());
        navigation.setType(xn7020001Req.getType());
        navigation.setParentCode(xn7020001Req.getParentCode());
        navigation.setStatus(xn7020001Req.getStatus());
        navigation.setOrderNo(xn7020001Req.getOrderNo());
        navigation.setCreator(xn7020001Req.getCreator());
        navigation.setCreateDatetime(xn7020001Req.getCreateDatetime());
        navigation.setRemark(xn7020001Req.getRemark());
        return navigation;
    }

    // 修改
    public static Navigation convert(XN7020103Req xn7020003Req) {
        Navigation navigation = new Navigation();
        navigation.setCode(xn7020003Req.getCode());
        navigation.setTitle(xn7020003Req.getTitle());
        navigation.setNavUrl(xn7020003Req.getNavUrl());
        navigation.setType(xn7020003Req.getType());
        navigation.setParentCode(xn7020003Req.getParentCode());
        navigation.setStatus(xn7020003Req.getStatus());
        navigation.setOrderNo(xn7020003Req.getOrderNo());
        navigation.setUpdater(xn7020003Req.getUpdater());
        navigation.setUpdateDatetime(xn7020003Req.getUpdateDatetime());
        navigation.setRemark(xn7020003Req.getRemark());
        return navigation;
    }

    // 列表分页查询
    public static Navigation convert(XN7020201Req xn7020151Req) {
        Navigation navigation = new Navigation();
        navigation.setCode(xn7020151Req.getCode());
        navigation.setTitle(xn7020151Req.getTitle());
        navigation.setType(xn7020151Req.getType());
        navigation.setParentCode(xn7020151Req.getParentCode());
        navigation.setStatus(xn7020151Req.getStatus());
        navigation.setCreateDatetimeStart(DateUtil.getFrontDate(
            xn7020151Req.getDateStart(), false));
        navigation.setCreateDatetimeEnd(DateUtil.getFrontDate(
            xn7020151Req.getDateEnd(), true));
        String column = xn7020151Req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = "code";
        }
        navigation.setOrder(column, xn7020151Req.getOrderDir());
        return navigation;
    }

    // 列表查询
    public static Navigation convert(XN7020202Req xn7020152Req) {
        Navigation navigation = new Navigation();
        navigation.setCode(xn7020152Req.getCode());
        navigation.setTitle(xn7020152Req.getTitle());
        navigation.setType(xn7020152Req.getType());
        navigation.setParentCode(xn7020152Req.getParentCode());
        navigation.setStatus(xn7020152Req.getStatus());
        return navigation;
    }
}
