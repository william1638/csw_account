package com.std.account.api.impl;

import com.std.account.ao.IAJourAO;
import com.std.account.api.AProcessor;
import com.std.account.dto.req.XN802613Req;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 * 对账审批:免对账
 * @author: myb858 
 * @since: 2016年1月17日 下午1:31:48 
 * @history:
 */
public class XN802613 extends AProcessor {
    private IAJourAO aJourAO = SpringContextHolder.getBean(IAJourAO.class);

    private XN802613Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        // TODO Auto-generated method stub

    }

}
