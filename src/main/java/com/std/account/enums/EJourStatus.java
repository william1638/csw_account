package com.std.account.enums;

/**
 * @author: xieyj 
 * @since: 2016年11月11日 上午10:07:50 
 * @history:
 */
public enum EJourStatus {
    todoCallBack("0", "刚生成待回调"), todoCheck("1", "已回调通过,待对账"), callBack_NO("2",
            "回调不通过"), Checked_YES("3", "已对账且账已平"), Checked_NO("4", "帐不平待调账"), Adjusted(
            "5", "已调账"), noChecked("9", "无需对账"), todoAdjust("6", "待审批"), adjusted_YES(
            "7", "审批通过"), adjusted_NO("8", "审批不通过");
    // 调账状态订单都无需对账

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
