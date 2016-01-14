/**
 * @Title EAccountState.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午2:42:59 
 * @version V1.0   
 */
package com.std.account.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午2:42:59 
 * @history:
 */
public enum EAccountStatus {
    // 0正常,1程序锁定,2人工锁定
    NORMAL("0", "正常"), LI_LOCK("1", "程序锁定"), RG_LOCK("2", "人工锁定");
    public static Map<String, EAccountStatus> getAccountStatusMap() {
        Map<String, EAccountStatus> map = new HashMap<String, EAccountStatus>();
        for (EAccountStatus status : EAccountStatus.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EAccountStatus(String code, String value) {
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
