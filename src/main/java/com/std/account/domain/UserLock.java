/**
 * @Title UserLock.java 
 * @Package com.ibis.pz.domain 
 * @Description 
 * @author miyb  
 * @date 2015-2-9 下午2:24:32 
 * @version V1.0   
 */
package com.std.account.domain;

import com.std.account.dao.base.ABaseDO;

/** 
 * @author: miyb 
 * @since: 2015-2-9 下午2:24:32 
 * @history:
 */
public class UserLock extends ABaseDO {

    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 3343732723909565641L;

    private Long id;

    // userId
    private String userId;

    // 用锁方向：1用锁/0解锁
    private String lockDirection;

    // 备注
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLockDirection() {
        return lockDirection;
    }

    public void setLockDirection(String lockDirection) {
        this.lockDirection = lockDirection;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserLockDO [id=" + id + ", userId=" + userId
                + ", lockDirection=" + lockDirection + ", remark=" + remark
                + "]";
    }

}
