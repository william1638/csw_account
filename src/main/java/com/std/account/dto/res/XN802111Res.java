package com.std.account.dto.res;

public class XN802111Res {
    // 是否成功
    private boolean isSuccess;

    public XN802111Res() {
    };

    public XN802111Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
