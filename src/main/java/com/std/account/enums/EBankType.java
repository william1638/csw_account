/**
 * @Title EBankType.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-6-16 下午3:12:34 
 * @version V1.0   
 */
package com.std.account.enums;

/** 
 * @author: miyb 
 * @since: 2015-6-16 下午3:12:34 
 * @history:
 */
public enum EBankType {
    LOCAL("1", "地区银行"), STATE("2", "国家银行");

    EBankType(String code, String value) {
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
