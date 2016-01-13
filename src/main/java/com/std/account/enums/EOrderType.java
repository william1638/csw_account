/**
 * @Title EOrderType.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-5-6 下午2:53:15 
 * @version V1.0   
 */
package com.std.account.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: miyb 
 * @since: 2015-5-6 下午2:53:15 
 * @history:
 */
public enum EOrderType {
    CQ("CQ", "充值取现"), HL("HL", "红冲蓝补"), XNB("XNB", "虚拟币"), ZZ("ZZ", "转入转出"), YT(
            "YT", "用途");
    public static Map<String, EOrderType> getOrderTypeMap() {
        Map<String, EOrderType> map = new HashMap<String, EOrderType>();
        for (EOrderType orderType : EOrderType.values()) {
            map.put(orderType.getCode(), orderType);
        }
        return map;
    }

    EOrderType(String code, String value) {
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
