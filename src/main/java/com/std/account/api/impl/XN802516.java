/**
 * @Title XN802516.java 
 * @Package com.std.account.api.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年12月25日 下午3:49:18 
 * @version V1.0   
 */
package com.std.account.api.impl;

import com.std.account.ao.IAccountAO;
import com.std.account.api.AProcessor;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/** 
 * 调账
 * @author: xieyj 
 * @since: 2016年12月25日 下午3:49:18 
 * @history:
 */
public class XN802516 extends AProcessor {
    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return null;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
    }

}
