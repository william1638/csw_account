package com.std.account.util.fuiou;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpPoster {
    private String responseString = "";

    public HttpPoster() {
    }

    /**
     * 使用http协议发送xmltext到url
     * @param url   将要发送的地址
     * @param xmltext  将要发送的内容
     * @return   http返回
     */
    public String post(String url, Map parameters) throws IOException {
        PostMethod xmlpost;
        int responseStatCode = 0;
        HttpClient httpclient = new HttpClient();
        httpclient.setConnectionTimeout(1000 * 30);
        xmlpost = new PostMethod(url);
        httpclient.getParams().setParameter(
            HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

        try {
            NameValuePair[] nameValuePairs = new NameValuePair[parameters
                .size()];
            Iterator keyIterator = parameters.keySet().iterator();
            int i = 0;
            while (keyIterator.hasNext()) {
                Object key = keyIterator.next();
                String value = (String) parameters.get(key);
                NameValuePair name = new NameValuePair((String) key, value);
                nameValuePairs[i] = name;
                i++;
            }

            xmlpost.setRequestBody(nameValuePairs);
            responseStatCode = httpclient.executeMethod(xmlpost);
            this.responseString = xmlpost.getResponseBodyAsString();
        } catch (IOException ex2) {
            System.out.println("报文发送到[" + url + "]失败:" + ex2.getMessage());
            throw ex2;
        }
        System.out.println("HTTP返回码=" + responseStatCode);
        System.out.println("应答数据=" + responseString);
        return responseString;

    }

    public String getResponseString() {
        return responseString;
    }

    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("LOGOTP", "1");
        map.put("ENCTP", "0");
        map.put("VERSION", "2.0");
        map.put("MCHNTCD", "0001000F0358674");
        map.put(
            "FM",
            "<ORDER><MCHNTCD>0001000F0358674</MCHNTCD>"
                    + "<TYPE>10</TYPE>"
                    + "<VERSION>2.0</VERSION>"
                    + "<LOGOTP>1</LOGOTP>"
                    + "<MCHNTORDERID>CQ201611081230599424</MCHNTORDERID>"
                    + "<USERID>253318742349</USERID>"
                    + "<AMT>2000</AMT>"
                    + "<BANKCARD>1234567890123456</BANKCARD>"
                    + "<BACKURL>http://10.201.31.2:8080/pay_test/result.jsp</BACKURL>"
                    + "<REURL>http://10.201.31.2:8080/pay_test/result.jsp</REURL>"
                    + "<HOMEURL>http://10.201.31.2:8080/pay_test/result.jsp</HOMEURL>"
                    + "<TYPE>11</TYPE>" + "<NAME>许淑琳</NAME>"
                    + "<IDTYPE>0</IDTYPE>" + "<IDNO>330184199308124326</IDNO>"
                    + "<REM1>20160927001253318742349</REM1>"
                    + "<REM2>20160927001253318742349</REM2>"
                    + "<REM3>20160927001253318742349</REM3>"
                    + "<SIGNTP>md5</SIGNTP>"
                    + "<SIGN>d8n0dh23w2yzrnez52ocqb4ckzp7t0fs</SIGN></ORDER>");

        HttpPoster poster = new HttpPoster();
        try {
            System.out.println(poster.post(
                "http://www-1.fuiou.com:18670/mobile_pay/h5pay/payAction.pay",
                map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
