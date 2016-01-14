/**
 * @Title UserIdGenerater.java 
 * @Package com.ibis.pz.utils 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 下午1:18:56 
 * @version V1.0   
 */
package com.std.account.core;

import java.util.Date;
import java.util.Random;

import com.std.account.common.DateUtil;

/** 
 * @author: miyb 
 * @since: 2015-3-7 下午1:18:56 
 * @history:
 */
public class UserIdGenerater {
    public static String generate() {
        int random = Math.abs(new Random().nextInt()) % 100000000;
        String today = DateUtil.dateToStr(new Date(),
            DateUtil.DATA_TIME_PATTERN_3) + String.valueOf(random);
        return today;
    }

    public static void main(String[] args) {
        String a = UserIdGenerater.generate();
        System.out.println(a);
    }

}
