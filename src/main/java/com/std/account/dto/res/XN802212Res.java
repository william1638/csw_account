package com.std.account.dto.res;

public class XN802212Res {
    // 是否成功
    private boolean isSuccess;

    public XN802212Res() {
    };

    public XN802212Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
