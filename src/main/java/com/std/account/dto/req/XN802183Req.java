/**
 * @Title XN802180Req.java 
 * @Package com.std.account.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午9:25:46 
 * @version V1.0   
 */
package com.std.account.dto.req;

/** 
 * 微信公众号支付回调
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午9:25:46 
 * @history:
 */
public class XN802183Req {

    // 回调结果（必填）
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
