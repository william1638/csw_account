/**
 * @Title IUserLockBO.java 
 * @Package com.ibis.pz 
 * @Description 
 * @author miyb  
 * @date 2015-3-7 上午9:17:53 
 * @version V1.0   
 */
package com.std.account.bo;

import java.util.List;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.UserLock;

/** 
 * @author: miyb 
 * @since: 2015-3-7 上午9:17:53 
 * @history:
 */
public interface IUserLockBO extends IPaginableBO<UserLock> {
    public int saveUserLock(UserLock data);

    public int refreshLockDirection(UserLock data);

    public UserLock getUserLock(Long id);

    public List<UserLock> queryUserLockList(UserLock data);
}
