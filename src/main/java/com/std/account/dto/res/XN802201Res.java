package com.std.account.dto.res;

public class XN802201Res {
    // 订单号
    private String cqNo;

    public XN802201Res() {
    }

    public XN802201Res(String cqNo) {
        this.cqNo = cqNo;
    }

    public String getCqNo() {
        return cqNo;
    }

    public void setCqNo(String cqNo) {
        this.cqNo = cqNo;
    }
}
