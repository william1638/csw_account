package com.std.account.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xieyj 
 * @since: 2016年9月3日 上午9:42:23 
 * @history:
 */
public enum EZzType {
    ZZ("1", "转账"), HZ("2", "划账");
    public static Map<String, EZzType> getDirectionMap() {
        Map<String, EZzType> map = new HashMap<String, EZzType>();
        for (EZzType direction : EZzType.values()) {
            map.put(direction.getCode(), direction);
        }
        return map;
    }

    EZzType(String code, String value) {
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
