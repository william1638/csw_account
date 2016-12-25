package com.std.account.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:07:50 
 * @history:
 */
public enum EJourStatus {
    todoCallBack("0", "刚生成待回调"), todoCheck("1", "已回调通过,待对账"), callBack_NO("2",
            "回调不通过"), Checked_YES("3", "对账通过"), Checked_NO("4", "对账不通过待调账"), Adjusted(
            "5", "已调账"), noCheck("9", "无需对账");

    EJourStatus(String code, String value) {
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
