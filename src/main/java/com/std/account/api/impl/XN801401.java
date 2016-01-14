package com.std.account.api.impl;

import com.std.account.ao.IUserExtAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PropertiesUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.UserExt;
import com.std.account.dto.req.XN801401Req;
import com.std.account.dto.res.XN801401Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 根据userId获取用户拓展信息
 * @author: myb858 
 * @since: 2015年9月18日 上午10:24:51 
 * @history:
 */
public class XN801401 extends AProcessor {
    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN801401Req req = null;

    @Override
    public XN801401Res doBusiness() throws BizException {
        UserExt userExt = userExtAO.doGetUserExt(req.getUserId());
        XN801401Res res = new XN801401Res();
        res.setPhoto(PropertiesUtil.getProperty("PHOTO_URL"));
        if (userExt != null) {
            res.setUserId(userExt.getUserId());
            res.setPhoto(userExt.getPhoto());
            res.setComefrom(userExt.getComefrom());
            res.setBirthday(userExt.getBirthday());
            res.setQq(userExt.getQq());
            res.setEmail(userExt.getEmail());
            res.setOccupation(userExt.getOccupation());
            res.setEducation(userExt.getEducation());
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801401Req.class);
        StringValidater.validateBlank(req.getUserId());

    }

}
