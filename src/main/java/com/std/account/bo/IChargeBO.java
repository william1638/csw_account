package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Charge;
import com.std.account.enums.EBoolean;

public interface IChargeBO extends IPaginableBO<Charge> {
    /*
     * public String saveChargeOffline(String fromAccountNumber, String
     * accountNumber, Long amount, Long price, EFromType fromType, String
     * fromCode, String pdf, String applyUser);
     */

    public String saveChargeOffline(Charge data);

    public void refreshApproveOrder(String chargeNo, String approveUser,
            EBoolean approveResult, String approveNote, String refNo, Long fee);

    public Charge getCharge(String code);

}
