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
public enum EArticleStatus {
    DRAFT("0", "草稿"), PUBLISHED("1", "已发布"), CANCEL("2", "作废");

    public static Map<String, EArticleStatus> getArticleStatusMap() {
        Map<String, EArticleStatus> map = new HashMap<String, EArticleStatus>();
        for (EArticleStatus status : EArticleStatus.values()) {
            map.put(status.getCode(), status);
        }
        return map;
    }

    EArticleStatus(String code, String value) {
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
