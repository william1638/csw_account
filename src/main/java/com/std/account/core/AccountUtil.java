/**
 * @Title AccountUtil.java 
 * @Package com.ibis.account.core 
 * @Description 
 * @author miyb  
 * @date 2015-2-26 下午4:54:24 
 * @version V1.0   
 */
package com.std.account.core;

import com.std.account.common.MD5Util;
import com.std.account.enums.ECurrency;
import com.std.account.enums.ESysAccount;

/** 
 * @author: miyb 
 * @since: 2015-2-26 下午4:54:24 
 * @history:
 */
public class AccountUtil {

    private static String key = "123321";

    public static String md5(Long amount) {
        StringBuffer bf = new StringBuffer(key);
        bf.append(amount);
        return MD5Util.md5(bf.toString());
    }

    public static String md5(String preMd5, Long preAmount, Long nowAmount) {
        // if (preMd5 != null && !preMd5.equals(md5(preAmount))) {
        // throw new BizException("xn000000", "账户金额已被篡改，请联系管理员");
        // }
        StringBuffer bf = new StringBuffer(key);
        bf.append(nowAmount);
        return MD5Util.md5(bf.toString());
    }

    public static String getAccountNumber(String currency) {
        String accountNumber = null;
        if (ECurrency.CNY.getCode().equals(currency)) {
            accountNumber = ESysAccount.CNY.getCode();
        } else if (ECurrency.FRB.getCode().equals(currency)) {
            accountNumber = ESysAccount.FRB.getCode();
        } else if (ECurrency.GXJL.getCode().equals(currency)) {
            accountNumber = ESysAccount.GXJL.getCode();
        } else if (ECurrency.QBB.getCode().equals(currency)) {
            accountNumber = ESysAccount.QBB.getCode();
        } else if (ECurrency.GWB.getCode().equals(currency)) {
            accountNumber = ESysAccount.GWB.getCode();
        } else if (ECurrency.HBB.getCode().equals(currency)) {
            accountNumber = ESysAccount.HBB.getCode();
        } else if (ECurrency.HBYJ.getCode().equals(currency)) {
            accountNumber = ESysAccount.HBYJ.getCode();
        }
        return accountNumber;
    }
}
