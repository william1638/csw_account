package com.std.account.dto.req;

public class XN702983Req {
    // 应用用户的唯一编号（目前暂用UserId,后期改进）（必填）
    private String tokenId;

    // （必填）
    private String userId;

    // 金额（精确到厘:正数为转入；负数为转出）Long（必填）
    private String amount;

    // 对方系统（必填）
    private String oppositeSystem;

    // 对方账号(选填)
    private String oppositeAccount;

    // 备注(选填)
    private String remark;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOppositeSystem() {
        return oppositeSystem;
    }

    public void setOppositeSystem(String oppositeSystem) {
        this.oppositeSystem = oppositeSystem;
    }

    public String getOppositeAccount() {
        return oppositeAccount;
    }

    public void setOppositeAccount(String oppositeAccount) {
        this.oppositeAccount = oppositeAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
