package com.std.account.dto.res;

public class XN802601Res {
    // 是否成功
    private boolean isSuccess;

    public XN802601Res() {
    };

    public XN802601Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
