package com.std.account.dto.res;

public class XN702011Res {
    // 是否成功
    private boolean isSuccess;

    public XN702011Res() {
    }

    public XN702011Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
