package com.std.account.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:07:50 
 * @history:
 */
public enum EAccountJourStatus {
    todoCallBack("0", "刚生成待回调"), noCheck("1", "无需对账"), todoCheck("2", "已回调待对账"), Checked_YES(
            "3", "对账通过"), Checked_NO("4", "对账不通过待调账"), Adjusted("5", "已调账");

    EAccountJourStatus(String code, String value) {
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
