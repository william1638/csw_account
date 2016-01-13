package com.std.account.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 导航状态
 * @author: xieyj 
 * @since: 2016年1月5日 下午9:19:00 
 * @history:
 */
public enum ENavStatus {
    UNUSE("0", "未使用"), USERED("1", "已使用");

    public static Map<String, ENavStatus> getNavStatusMap() {
        Map<String, ENavStatus> map = new HashMap<String, ENavStatus>();
        for (ENavStatus status : ENavStatus.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    ENavStatus(String code, String value) {
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
