package com.std.account.enums;

import java.util.HashMap;
import java.util.Map;

public enum EChannelType {
    Yeepay_PC("11", "易宝支付-网关"), Yeepay_WAP("12", "易宝支付-WAP"), Baofoo_PC("13",
            "宝付支付-网关"), Baofoo_WAP("14", "宝付支付-WAP"), Fuiou_PC("15", "富友支付-网关"), Fuiou_WAP(
            "16", "富友支付-WAP"), Alipay("30", "支付宝支付"), 微信("35", "微信支付"), CZB(
            "01", "线下_橙账本"), NBZ("0", "内部账"), Adjust_ZH("9", "调账");

    public static Map<String, EChannelType> getChannelTypeResultMap() {
        Map<String, EChannelType> map = new HashMap<String, EChannelType>();
        for (EChannelType type : EChannelType.values()) {
            map.put(type.getCode(), type);
        }
        return map;
    }

    EChannelType(String code, String value) {
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
