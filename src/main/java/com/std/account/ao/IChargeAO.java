package com.std.account.ao;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.Charge;

public interface IChargeAO {

    String DEFAULT_ORDER_COLUMN = "code";

    Paginable<Charge> queryChargePage(int start, int limit, Charge condition);

    String doChargeOffline(String accountNumber, Long amount, String fromType,
            String fromCode, String pdf);

    String doChargeOfflineWithoutApp(String accountNumber, Long amount,
            String fromType, String fromCode, String pdf, String updater,
            String remark);

    void doApproveCharge(String chargeNo, String approveUser,
            String approveResult, String approveNote, String refNo, Long fee);

}
