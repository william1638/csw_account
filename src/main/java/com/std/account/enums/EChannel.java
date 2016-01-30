/**
 * @Title EChannel.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午3:40:33 
 * @version V1.0   
 */
package com.std.account.enums;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午3:40:33 
 * @history:
 */
public enum EChannel {
    LDYS("11", "线上-联动优势"), TLZF("12", "线上-通联支付"), YBZF("13", "线上-易宝支付"), BFZF(
            "14", "线上-宝付支付"), OFFLINE("01", "线下");

    EChannel(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
