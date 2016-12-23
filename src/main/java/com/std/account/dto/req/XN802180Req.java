/**
 * @Title XN802180Req.java 
 * @Package com.std.account.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午9:25:46 
 * @version V1.0   
 */
package com.std.account.dto.req;

/** 
 * 微信APP支付请求接口，返回预付单信息
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午9:25:46 
 * @history:
 */
public class XN802180Req {
    // 系统编号（必填）
    private String systemCode;

    // 商品描述（必填）
    // 商品描述交易字段格式根据不同的应用场景按照以下格式：
    // APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
    private String body;

    // 总金额（必填）—— 订单总金额，单位为分
    private String totalFee;

    // 终端IP（必填）—— 用户端实际ip
    private String spbillCreateIp;

    // 通知地址（必填）—— 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
    private String notifyUrl;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

}
