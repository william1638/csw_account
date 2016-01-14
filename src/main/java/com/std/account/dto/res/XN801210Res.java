package com.std.account.dto.res;

public class XN801210Res {
    // 是否成功
    private boolean isSuccess;

    public XN801210Res() {
    };

    public XN801210Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
