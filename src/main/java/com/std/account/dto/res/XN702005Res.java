package com.std.account.dto.res;

public class XN702005Res {
    // 是否成功
    private boolean isSuccess;

    public XN702005Res() {
    };

    public XN702005Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
