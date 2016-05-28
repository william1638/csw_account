package com.std.account.bo;

import com.std.account.bo.base.IPaginableBO;
import com.std.account.domain.Charge;
import com.std.account.enums.EBoolean;
import com.std.account.enums.EFromType;

public interface IChargeBO extends IPaginableBO<Charge> {

    public String saveChargeOffline(String accountNumber, Long amount,
            EFromType fromType, String fromCode);

    public void refreshApproveOrder(String chargeNo, String approveUser,
            EBoolean approveResult, String approveNote, String refNo, Long fee);

    public Charge getCharge(String code);

}
