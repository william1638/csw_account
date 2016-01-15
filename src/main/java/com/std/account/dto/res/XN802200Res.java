package com.std.account.dto.res;

public class XN802200Res {
    // 订单号
    private String cqNo;

    public XN802200Res() {
    }

    public XN802200Res(String cqNo) {
        this.cqNo = cqNo;
    }

    public String getCqNo() {
        return cqNo;
    }

    public void setCqNo(String cqNo) {
        this.cqNo = cqNo;
    }
}
