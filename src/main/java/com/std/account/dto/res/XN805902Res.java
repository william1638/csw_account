package com.std.account.dto.res;

public class XN805902Res {
    // 是否成功
    private boolean isSuccess;

    public XN805902Res() {
    };

    public XN805902Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}