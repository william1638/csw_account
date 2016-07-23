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
    AJ_CZ("11", "充值"), AJ_QXCG("-11", "取现成功"), AJ_ZR("12", "转入"), AJ_ZC("-12",
            "转出"), AJ_LB("19", "蓝补"), AJ_HC("-19", "红冲"), AJ_QXDJ("-110",
            "取现冻结"), AJ_QXJD("-111", "取现解冻"), AJ_RZJ("21", "注册送积分"), AJ_RJQ(
            "-21", "注册扣积分"), AJ_TZJ("22", "活动送积分"), AJ_TJQ("-22", "活动扣积分"), AJ_GMJJF(
            "31", "购买加积分"), AJ_GMKJF("-31", "购买扣积分"), AJ_DXKJF("32", "兑现扣积分"), AJ_DXJJF(
            "-32", "兑现加积分"), AJ_DXDJ("33", "兑现冻结"), AJ_DXJD("-33", "兑现解冻");
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
