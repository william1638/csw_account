package com.std.account.ao;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.SmsCaptcha;

public interface ISmsCaptchaAO {

    String DEFAULT_ORDER_COLUMN = "id";

    public boolean sentSmsCaptcha(String mobile, String bizType);

    public Paginable<SmsCaptcha> querySmsCaptchaPage(int start, int limit,
            SmsCaptcha condition);

}
