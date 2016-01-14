/**
 * @Title EBizType.java 
 * @Package com.ibis.account.enums 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午2:15:22 
 * @version V1.0   
 */
package com.std.account.enums;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午2:15:22 
 * @history:
 */
public enum EBizType {
    AJ_XNB("10", "虚拟币兑换"), AJ_CZ("11", "充值"), AJ_QX("-11", "取现"), AJ_ZR("12",
            "转入"), AJ_ZC("-12", "转出"), AJ_JD("13", "解冻"), AJ_DJ("-13", "冻结"), AJ_LB(
            "19", "蓝补"), AJ_HC("-19", "红冲");
    public static Map<String, EBizType> getBizTypeMap() {
        Map<String, EBizType> map = new HashMap<String, EBizType>();
        for (EBizType bizType : EBizType.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EBizType(String code, String value) {
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
