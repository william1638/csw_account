package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.UserExt;

/**
 * @author: luoqi 
 * @since: 2015年3月8日 上午10:51:31 
 * @history:
 */
public interface IUserExtBO extends IPaginableBO<UserExt> {
    public int saveUserExt(UserExt data);

    public int removeUserExt(String userId);

    public int refreshUserExt(UserExt data);

    public int refreshPhoto(String userId, String photo);

    public UserExt getUserExt(String userId);

    public List<UserExt> queryUserExtList(UserExt data);
}
