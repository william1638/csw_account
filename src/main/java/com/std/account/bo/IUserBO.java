package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.User;
import com.std.account.dto.res.XN805901Res;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:13 
 * @history:
 */
public interface IUserBO extends IPaginableBO<User> {
    /**
     * 获取远程用户信息
     * @param tokenId
     * @param userId
     * @return 
     * @create: 2016年5月30日 下午3:00:44 xieyj
     * @history:
     */
    public XN805901Res getRemoteUser(String tokenId, String userId);

}
