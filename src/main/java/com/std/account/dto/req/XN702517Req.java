/**
 * @Title XN702517Req.java 
 * @Package com.xnjr.account.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年11月18日 下午10:19:41 
 * @version V1.0   
 */
package com.std.account.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2015年11月18日 下午10:19:41 
 * @history:
 */
public class XN702517Req {

    // 易宝回调加密数据
    private String data;

    // 易宝回调加密的KEY
    private String encryptkey;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEncryptkey() {
        return encryptkey;
    }

    public void setEncryptkey(String encryptkey) {
        this.encryptkey = encryptkey;
    }
}
