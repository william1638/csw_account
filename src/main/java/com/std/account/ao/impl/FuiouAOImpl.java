package com.std.account.ao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fuiou.mpay.encrypt.DESCoderFUIOU;
import com.std.account.ao.IFuiouAO;
import com.std.account.domain.ChannelCompany;
import com.std.account.domain.FuiouPC;
import com.std.account.domain.FuiouWAP;
import com.std.account.dto.req.XN802150Req;
import com.std.account.dto.req.XN802151Req;
import com.std.account.dto.req.XN802152Req;
import com.std.account.dto.req.XN802153Req;
import com.std.account.dto.res.ChannelCallbackRes;
import com.std.account.exception.BizException;
import com.std.account.util.MD5;

@Service
public class FuiouAOImpl implements IFuiouAO {
    // @Autowired
    // IChannelCompanyBO channelCompanyBO;

    @Override
    public String getWapPayUrl(XN802152Req req) {
        FuiouWAP fuiouWAP = new FuiouWAP();
        // ChannelCompany channelCompany = channelCompanyBO.getChannelCompany(
        // req.getCompanyCode(), EChannelType.Fuiou, EPayType.WAP);
        ChannelCompany channelCompany = getWAPCompany();
        // 开始组装

        String ENCTP = fuiouWAP.getENCTP();
        String VERSION = fuiouWAP.getVERSION();
        String MCHNTCD = channelCompany.getPaycompany();// 商户代码
        String LOGOTP = fuiouWAP.getLOGOTP();
        // 开始组装FM
        String FM = null;

        String mchntOrderId = req.getMchntOrderId();
        String userId = req.getUserId();
        String amt = req.getAmt();
        String bankCard = req.getBankCard();
        String name = req.getName();
        String idType = req.getIdType();
        String idNo = req.getIdNo();
        String type = fuiouWAP.getTYPE();
        String BACK_URL = channelCompany.getBackUrl();
        String RETURN_URL = channelCompany.getErrorUrl();
        String HOME_URL = channelCompany.getPageUrl();
        String MCHNT_KEY = channelCompany.getPrivatekey();
        // MD5
        String signPlain = type + "|" + VERSION + "|" + MCHNTCD + "|"
                + mchntOrderId + "|" + userId + "|" + amt + "|" + bankCard
                + "|" + BACK_URL + "|" + name + "|" + idNo + "|" + idType + "|"
                + LOGOTP + "|" + HOME_URL + "|" + RETURN_URL + "|" + MCHNT_KEY;
        String sign = MD5.MD5Encode(signPlain);
        System.out.println("[签名明文:]" + signPlain + "[签名密文:]" + sign);

        StringBuffer orderPlain = new StringBuffer();
        orderPlain.append("<ORDER>")

        .append("<MCHNTCD>").append(MCHNTCD).append("</MCHNTCD>")

        .append("<TYPE>").append(type).append("</TYPE>")

        .append("<VERSION>").append(VERSION).append("</VERSION>")

        .append("<LOGOTP>").append(LOGOTP).append("</LOGOTP>")

        .append("<MCHNTORDERID>").append(mchntOrderId)
            .append("</MCHNTORDERID>")

            .append("<USERID>").append(userId).append("</USERID>")

            .append("<AMT>").append(amt).append("</AMT>")

            .append("<BANKCARD>").append(bankCard).append("</BANKCARD>")

            .append("<BACKURL>").append(BACK_URL).append("</BACKURL>")

            .append("<REURL>").append(RETURN_URL).append("</REURL>")

            .append("<HOMEURL>").append(HOME_URL).append("</HOMEURL>")

            .append("<TYPE>11</TYPE>")

            .append("<NAME>").append(name).append("</NAME>")

            .append("<IDTYPE>").append(idType).append("</IDTYPE>")

            .append("<IDNO>").append(idNo).append("</IDNO>")

            .append("<REM1>").append(userId).append("</REM1>")

            .append("<REM2>").append(userId).append("</REM2>")

            .append("<REM3>").append(userId).append("</REM3>")

            .append("<SIGNTP>").append("md5").append("</SIGNTP>")

            .append("<SIGN>").append(sign).append("</SIGN>")

            .append("</ORDER>");
        System.out.println("[订单信息:]" + orderPlain.toString());

        try {
            FM = DESCoderFUIOU.desEncrypt(orderPlain.toString(),
                DESCoderFUIOU.getKeyLength8(MCHNT_KEY));
        } catch (Exception e) {
            throw new BizException("xn802151", "富友WAP端支付加密FM出错");
        }

        return fuiouWAP.getPayUrl() + "?ENCTP=" + ENCTP + "&VERSION=" + VERSION
                + "&LOGOTP=" + LOGOTP + "&MCHNTCD=" + MCHNTCD + "&FM=" + FM;
    }

    @Override
    public String getPCPayUrl(XN802150Req req) {
        FuiouPC fuiouPC = new FuiouPC();
        // ChannelCompany channelCompany = channelCompanyBO.getChannelCompany(
        // req.getCompanyCode(), EChannelType.Fuiou, EPayType.PC);
        ChannelCompany channelCompany = getPCCompany();
        // 开始组装
        String mchnt_cd = channelCompany.getPaycompany();// 商户代码
        String order_id = req.getOrderId();// 商户订单号
        String order_amt = req.getOrderAmt();// 交易金额
        String order_pay_type = req.getOrderPayType();// 支付类型
        String page_notify_url = channelCompany.getPageUrl();// 页面跳转 URL

        String back_notify_url = channelCompany.getBackUrl();// 后台通知 URL
        String order_valid_time = fuiouPC.getOrderValidTime();// 超时时间
        // String iss_ins_cd = req.getIssInsCd();// 银行代码
        String iss_ins_cd = getBank(req.getIssInsCd());// 银行代码
        String goods_name = req.getGoodsName();// 商品名称
        String goods_display_url = req.getGoodsDisplayUrl();// 商品展示网址

        String rem = req.getRem();// 备注
        String ver = fuiouPC.getVer();// 版本号
        String md5 = null;// MD5 摘要数据
        String mchnt_key = channelCompany.getPrivatekey(); // 32位的商户密钥
        String signDataStr = mchnt_cd + "|" + order_id + "|" + order_amt + "|"
                + order_pay_type + "|" + page_notify_url + "|"
                + back_notify_url + "|" + order_valid_time + "|" + iss_ins_cd
                + "|" + goods_name + "|" + goods_display_url + "|" + rem + "|"
                + ver + "|" + mchnt_key;
        md5 = MD5.MD5Encode(signDataStr);
        return fuiouPC.getPayUrl() + "?mchnt_cd=" + mchnt_cd + "&order_id="
                + order_id + "&order_amt=" + order_amt + "&order_pay_type="
                + order_pay_type + "&page_notify_url=" + page_notify_url
                + "&back_notify_url=" + back_notify_url + "&order_valid_time="
                + order_valid_time + "&iss_ins_cd=" + iss_ins_cd
                + "&goods_name=" + goods_name + "&goods_display_url="
                + goods_display_url + "&rem=" + rem + "&ver=" + ver + "&md5="
                + md5 + "&mchnt_key=" + mchnt_key;
    }

    private String getBank(String issInsCd) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("CEB", "0803030000");
        return map.get(issInsCd);
    }

    private ChannelCompany getPCCompany() {
        ChannelCompany company = new ChannelCompany();
        company.setBackUrl("http://10.201.31.2:8080/pay_test/result.jsp");
        company.setPageUrl("http://10.201.31.2:8080/pay_test/result.jsp");
        company.setPaycompany("0001000F0040992");
        company.setPrivatekey("vau6p7ldawpezyaugc0kopdrrwm4gkpu");
        return company;
    }

    private ChannelCompany getWAPCompany() {
        ChannelCompany company = new ChannelCompany();
        company.setBackUrl("http://10.201.31.2:8080/pay_test/result.jsp");
        company.setPageUrl("http://10.201.31.2:8080/pay_test/result.jsp");
        company.setErrorUrl("http://10.201.31.2:8080/pay_test/result.jsp");
        company.setPaycompany("0001000F0040992");
        company.setPrivatekey("vau6p7ldawpezyaugc0kopdrrwm4gkpu");
        return company;
    }

    @Override
    public ChannelCallbackRes handlePCPay(XN802151Req req) {
        // ChannelCompany channelCompany = channelCompanyBO.getChannelCompany(
        // req.getCompanyCode(), EChannelType.Fuiou, EPayType.PC);
        ChannelCompany channelCompany = getPCCompany();
        // 开始解析
        String mchnt_cd = req.getMchntCd();
        String order_id = req.getOrderId();
        String order_amt = req.getOrderAmt();
        String order_date = req.getOrderDate();
        String order_st = req.getOrderSt();
        String order_pay_code = req.getOrderPayCode();
        String order_pay_error = req.getOrderPayError();
        String fy_ssn = req.getFySsn();
        String resv1 = req.getResv1();
        String md5 = req.getMd5();
        // 验证数据合法性
        String mchnt_key = channelCompany.getPrivatekey(); // 32位的商户密钥
        String signDataStr = mchnt_cd + "|" + order_id + "|" + order_date + "|"
                + order_amt + "|" + order_st + "|" + order_pay_code + "|"
                + order_pay_error + "|" + resv1 + "|" + fy_ssn + "|"
                + mchnt_key;
        String md52 = MD5.MD5Encode(signDataStr);
        if (!md5.equalsIgnoreCase(md52)) {
            throw new BizException("xn802151", "富友PC端支付回调返回数据非法");
        }
        // 返回
        ChannelCallbackRes res = new ChannelCallbackRes();
        res.setChannelOrderNo(fy_ssn);
        res.setCompanyOrderNo(order_id);

        if ("0000".equalsIgnoreCase(order_pay_code)) {
            res.setIsSuccess(true);
            res.setErrorInfo("支付成功");
        } else {
            res.setIsSuccess(false);
            res.setErrorInfo(order_pay_error);
        }
        return res;
    }

    @Override
    public ChannelCallbackRes handleWAPPay(XN802153Req req) {
        // ChannelCompany channelCompany = channelCompanyBO.getChannelCompany(
        // req.getCompanyCode(), EChannelType.Fuiou, EPayType.WAP);
        ChannelCompany channelCompany = getWAPCompany();
        // 开始解析
        String version = req.getVersion();
        String type = req.getType();
        String responseCode = req.getResponseCode();
        String responseMsg = req.getResponseMsg();
        String mchntCd = req.getMchntCd();

        String mchntOrderId = req.getMchntOrderId();
        String orderId = req.getOrderId();
        String backCard = req.getBackCard();
        String amt = req.getAmt();
        String sign = req.getSign();
        // 验证数据合法性
        String mchnt_key = channelCompany.getPrivatekey(); // 32位的商户密钥
        String signPlain = type + "|" + version + "|" + responseCode + "|"
                + mchntCd + "|" + mchntOrderId + "|" + orderId + "|" + amt
                + "|" + backCard + "|" + mchnt_key;
        String sign2 = MD5.MD5Encode(signPlain);
        if (!sign.equalsIgnoreCase(sign2)) {
            throw new BizException("xn802151", "富友WAP端支付回调返回数据非法");
        }

        // 返回
        ChannelCallbackRes res = new ChannelCallbackRes();
        res.setChannelOrderNo(req.getOrderId());
        res.setCompanyOrderNo(req.getMchntOrderId());
        if ("0000".equalsIgnoreCase(responseCode)) {
            res.setIsSuccess(true);
            res.setErrorInfo("支付成功");
        } else {
            res.setIsSuccess(false);
            res.setErrorInfo(responseMsg);
        }
        return res;
    }
}
