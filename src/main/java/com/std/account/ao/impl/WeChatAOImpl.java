/**
 * @Title WeChatAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:21:03 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import com.std.account.ao.IWeChatAO;
import com.std.account.util.wechat.WXPrepay;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:21:03 
 * @history:
 */
public class WeChatAOImpl implements IWeChatAO {

    /** 
     * @see com.std.account.ao.IWeChatAO#getPrepayId(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public String getPrepayId(String systemCode, String body, Long totalFee,
            String spbillCreateIp, String notifyUrl) {
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid("wx3eb3d4d796093674");// 骑来骑去
        return prePay.submitXmlGetPrepayId();
    }

}
