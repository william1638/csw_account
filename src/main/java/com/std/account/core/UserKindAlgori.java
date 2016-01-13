/**
 * @Title UserKindAlgori.java 
 * @Package com.ibis.pz.algorithm 
 * @Description 
 * @author miyb  
 * @date 2015-4-16 上午7:41:16 
 * @version V1.0   
 */
package com.std.account.core;

import org.apache.commons.lang.StringUtils;

import com.std.account.exception.BizException;

/** 
 * @author: miyb 
 * @since: 2015-4-16 上午7:41:16 
 * @history:
 */
public class UserKindAlgori {
    // 身份[身份位（一位）+等级（三位，999级高于1级）
    // 1001表示1级借资人；2010表示10级又借资人又金主；3100表示纯金主；9001表示1级平台人员]
    private static final Integer Plat = 9000;

    public static void isPlatUser(String userKind) {
        if (StringUtils.isNotBlank(userKind)) {
            Integer userKindI = Integer.valueOf(userKind);
            if (userKindI.intValue() < Plat.intValue()) {
                throw new BizException("", "当前用户不是平台人员");
            }
        } else {
            throw new BizException("", "用户类型不能为空");
        }
    }

    public static void isFrontUser(String userKind) {
        if (StringUtils.isNotBlank(userKind)) {
            Integer userKindI = Integer.valueOf(userKind);
            if (userKindI.intValue() > Plat.intValue()) {
                throw new BizException("", "当前用户不是借资用户");
            }
        } else {
            throw new BizException("", "用户类型不能为空");
        }
    }

    public static void isMZCFrontUser(String userKind) {
        if (StringUtils.isNotBlank(userKind)) {
            Integer userKindI = Integer.valueOf(userKind);
            if (!(userKindI.intValue() > 2000 && userKindI.intValue() < 3000)) {
                throw new BizException("", "当前用户不是免注册的前端用户");
            }
        } else {
            throw new BizException("", "用户类型不能为空");
        }
    }

    /** 
     * @param userKind
     * @param toUser 
     * @create: 2015-4-17 下午8:55:49 miyb
     * @history: 
     */
    public static void checkAudience(String userKind, String toUser) {
        if (StringUtils.isNotBlank(userKind) && StringUtils.isNotBlank(toUser)) {
            Integer toUserI = Integer.valueOf(toUser);
            Integer userKindI = Integer.valueOf(userKind);
            if (toUserI.intValue() > userKindI.intValue()) {
                throw new BizException("jd02001", "当前用户不是该产品的受众");
            }
        } else {
            throw new BizException("", "用户类型或受众不能为空");
        }
    }
}
