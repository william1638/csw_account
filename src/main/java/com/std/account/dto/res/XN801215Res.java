package com.std.account.dto.res;

public class XN801215Res {
    // 是否成功
    private boolean isSuccess;

    public XN801215Res() {
    }

    public XN801215Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
