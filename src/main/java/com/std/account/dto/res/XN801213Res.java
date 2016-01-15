package com.std.account.dto.res;

public class XN801213Res {
    // 是否成功
    private boolean isSuccess;

    public XN801213Res() {
    }

    public XN801213Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
