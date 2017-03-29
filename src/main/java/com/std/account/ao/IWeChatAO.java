/**
 * @Title IWeChatAO.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:23:39 
 * @version V1.0   
 */
package com.std.account.ao;

import com.std.account.domain.CallbackResult;
import com.std.account.dto.res.XN002500Res;
import com.std.account.dto.res.XN002501Res;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:23:39 
 * @history:
 */
public interface IWeChatAO {

    public XN002500Res getPrepayIdApp(String fromUserId, String toUserId,
            String bizType, String fromBizNote, String toBizNote,
            Long transAmount, String payGroup);

    public XN002501Res getPrepayIdH5(String fromUserId, String fromOpenId,
            String toUserId, String bizType, String fromBizNote,
            String toBizNote, Long transAmount, String payGroup);

    public String getPrepayIdNative(String fromUserId, String toUserId,
            String bizType, String fromBizNote, String toBizNote,
            Long transAmount, String payGroup);

    public CallbackResult doCallbackAPP(String result);

    public CallbackResult doCallbackH5(String result);

    public String getAccessToken(String appId, String appSecret);

    public void doBizCallback(CallbackResult callbackResult);

}
