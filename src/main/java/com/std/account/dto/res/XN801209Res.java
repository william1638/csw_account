package com.std.account.dto.res;

public class XN801209Res {
    // 是否成功
    private boolean isSuccess;

    public XN801209Res() {
    }

    public XN801209Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
