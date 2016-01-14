/**
 * @Title EScoreType.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-3-17 下午8:01:32 
 * @version V1.0   
 */
package com.std.account.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: miyb 
 * @since: 2015-3-17 下午8:01:32 
 * @history:
 */
public enum EScoreType {
    TG("1", "推广积分"), HD("2", "活动积分"), TP_1("3", "三方积分1"), TP_2("4", "三方积分2");
    public static Map<String, EScoreType> getScoreTypeMap() {
        Map<String, EScoreType> map = new HashMap<String, EScoreType>();
        for (EScoreType scoreType : EScoreType.values()) {
            map.put(scoreType.getCode(), scoreType);
        }
        return map;
    }

    EScoreType(String code, String value) {
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
