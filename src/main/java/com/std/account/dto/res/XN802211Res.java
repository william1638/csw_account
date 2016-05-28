package com.std.account.dto.res;

public class XN802211Res {
    // 订单号
    private String cqNo;

    public XN802211Res() {
    }

    public XN802211Res(String cqNo) {
        this.cqNo = cqNo;
    }

    public String getCqNo() {
        return cqNo;
    }

    public void setCqNo(String cqNo) {
        this.cqNo = cqNo;
    }
}
