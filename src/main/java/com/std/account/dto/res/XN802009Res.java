package com.std.account.dto.res;

public class XN802009Res {
    // 是否成功
    private boolean isSuccess;

    public XN802009Res() {
    }

    public XN802009Res(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
