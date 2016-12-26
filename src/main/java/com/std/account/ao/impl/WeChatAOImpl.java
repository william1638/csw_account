/**
 * @Title WeChatAOImpl.java 
 * @Package com.std.account.ao.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年12月23日 上午11:21:03 
 * @version V1.0   
 */
package com.std.account.ao.impl;

import com.std.account.ao.IWeChatAO;
import com.std.account.util.wechat.WXPrepay;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月23日 上午11:21:03 
 * @history:
 */
public class WeChatAOImpl implements IWeChatAO {

    /** 
     * @see com.std.account.ao.IWeChatAO#getPrepayId(java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public String getPrepayIdApp(String systemCode, String body, Long totalFee,
            String spbillCreateIp, String notifyUrl) {
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid("wx3eb3d4d796093674");// 骑来骑去应用ID
        prePay.setMch_id(""); // 商户号
        prePay.setBody(""); // 商品描述
        prePay.setOut_trade_no(""); // 订单号
        prePay.setTotal_fee(""); // 订单总金额
        prePay.setSpbill_create_ip(""); // 用户IP
        prePay.setTrade_type("APP"); // 交易类型
        prePay.setNotify_url("http://www.weixin.qq.com/wxpay/pay.php");// 回调地址
        return prePay.submitXmlGetPrepayId();
    }

    @Override
    public String getPrepayIdH5(String systemCode, String body, Long totalFee,
            String spbillCreateIp, String notifyUrl) {
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid("gh_355fcfc82d3c");// 微信支付分配的公众账号ID
        prePay.setMch_id("1400666002"); // 商户号
        prePay.setBody("橙袋服务中心—橙商户产品购买"); // 商品描述
        prePay.setOut_trade_no(""); // 订单号
        prePay.setTotal_fee(""); // 订单总金额
        prePay.setSpbill_create_ip(""); // 用户IP
        prePay.setTrade_type("JSAPI"); // 交易类型
        prePay.setNotify_url("http://www.weixin.qq.com/wxpay/pay.php");// 回调地址
        return prePay.submitXmlGetPrepayId();
    }

}
