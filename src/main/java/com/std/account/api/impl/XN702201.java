package com.std.account.api.impl;

import com.std.account.ao.IUserExtAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.common.PropertiesUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.UserExt;
import com.std.account.dto.req.XN702201Req;
import com.std.account.dto.res.XN702201Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 根据userId获取用户拓展信息
 * @author: myb858 
 * @since: 2015年9月18日 上午10:24:51 
 * @history:
 */
public class XN702201 extends AProcessor {
    private IUserExtAO userExtAO = SpringContextHolder
        .getBean(IUserExtAO.class);

    private XN702201Req req = null;

    @Override
    public XN702201Res doBusiness() throws BizException {
        UserExt userExt = userExtAO.doGetUserExt(req.getUserId());
        XN702201Res res = new XN702201Res();
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
        req = JsonUtil.json2Bean(inputparams, XN702201Req.class);
        StringValidater.validateBlank(req.getUserId());

    }

}
