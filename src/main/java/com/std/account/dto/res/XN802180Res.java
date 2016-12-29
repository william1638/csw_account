/**
 * @Title XN802182Res.java 
 * @Package com.std.account.dto.res 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月26日 下午4:46:41 
 * @version V1.0   
 */
package com.std.account.dto.res;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午4:46:41 
 * @history:
 */
public class XN802180Res {
    // 微信预支付订单号
    private String prepayId;

    public XN802180Res(String prepayId) {
        super();
        this.prepayId = prepayId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
