package com.std.account.ao;

import com.std.account.bo.base.Paginable;
import com.std.account.domain.Charge;
import com.std.account.enums.ECurrency;

public interface IChargeAO {

    String DEFAULT_ORDER_COLUMN = "code";

    Paginable<Charge> queryChargePage(int start, int limit, Charge condition);

    String doChargeOffline(Charge data, ECurrency currency);

    String doChargeOfflineWithoutApp(Charge data, ECurrency currency);

    void doApproveCharge(String chargeNo, String approveUser,
            String approveResult, String approveNote, String refNo, Long fee,
            ECurrency currency);

}
