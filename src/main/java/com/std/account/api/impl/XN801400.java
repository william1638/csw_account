package com.std.account.api.impl;

import com.std.account.ao.IUserAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.User;
import com.std.account.dto.req.XN801400Req;
import com.std.account.dto.res.XN801400Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 根据userId获取用户信息
 * @author: myb858 
 * @since: 2015年8月23日 下午1:48:57 
 * @history:
 */
public class XN801400 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN801400Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        User user = userAO.doGetUser(req.getUserId());
        XN801400Res res = new XN801400Res();
        if (user != null) {
            res.setUserId(user.getUserId());
            res.setMobile(user.getMobile());
            res.setLoginPwdStrength(user.getLoginPwdStrength());
            res.setUserKind(user.getUserKind());
            res.setUserReferee(user.getUserReferee());
            res.setIdKind(user.getIdKind());
            res.setIdNo(user.getIdNo());
            res.setRealName(user.getRealName());
            res.setTradePwdStrength(user.getTradePwdStrength());
            res.setCreateDatetime(user.getCreateDatetime());
            res.setRemark(user.getRemark());
            res.setStatus(user.getStatus());
            res.setServeList(user.getServeList());
            res.setQuoteList(user.getQuoteList());
            res.setLevel(user.getLevel());
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN801400Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
