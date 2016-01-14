package com.std.account.dto.res;

public class XN801211Res {
    // 是否成功
    private boolean isSuccess;

    public XN801211Res() {
    }

    public XN801211Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
