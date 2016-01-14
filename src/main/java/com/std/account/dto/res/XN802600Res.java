package com.std.account.dto.res;

public class XN802600Res {
    // 订单号
    private String cqNo;

    public XN802600Res() {
    }

    public XN802600Res(String cqNo) {
        this.cqNo = cqNo;
    }

    public String getCqNo() {
        return cqNo;
    }

    public void setCqNo(String cqNo) {
        this.cqNo = cqNo;
    }
}
