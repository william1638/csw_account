/**
 * @Title EChannel.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午3:40:33 
 * @version V1.0   
 */
package com.std.account.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午3:40:33 
 * @history:
 */
public enum EChannel {
    // 1线上2线下
    LDYS("11", "线上-联动优势"), TLZF("12", "线上-通联支付"), YBZF("13", "线上-易宝支付"), OFFLINE(
            "01", "线下");
    public static Map<String, EChannel> getChannelMap() {
        Map<String, EChannel> map = new HashMap<String, EChannel>();
        for (EChannel channel : EChannel.values()) {
            map.put(channel.getCode(), channel);
        }
        return map;
    }

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
