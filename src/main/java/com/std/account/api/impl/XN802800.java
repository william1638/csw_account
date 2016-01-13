package com.std.account.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.std.account.ao.IZZOrderAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.StringValidater;
import com.std.account.domain.ZZOrder;
import com.std.account.dto.req.XN702900Req;
import com.std.account.dto.res.XN702900Res;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/**
 *统计指定账户各个应用的（转入-转出）值
 * 
 * SELECT t.opposite_system,sum(amount) FROM xn_account.tli_zzorder t where t.account_number='2015102960305217' group by t.opposite_system;
 * @author: myb858 
 * @since: 2015年10月27日 上午11:07:39 
 * @history:
 */
public class XN802800 extends AProcessor {
    private IZZOrderAO zzOrderAO = SpringContextHolder
        .getBean(IZZOrderAO.class);

    private XN702900Req xn702900Req = null;

    @Override
    public Object doBusiness() throws BizException {
        List<XN702900Res> res = new ArrayList<XN702900Res>();
        String accountNumber = xn702900Req.getAccountNumber();
        List<ZZOrder> list = zzOrderAO.doStatisticsDvalue(accountNumber);
        if (CollectionUtils.isNotEmpty(list)) {
            for (ZZOrder ele : list) {
                XN702900Res tem = new XN702900Res();
                tem.setAccountNumber(accountNumber);
                tem.setAmount(ele.getAmount());
                tem.setOppositeSystem(ele.getOppositeSystem());
                res.add(tem);
            }
        }
        return res;
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        xn702900Req = JsonUtil.json2Bean(inputparams, XN702900Req.class);
        StringValidater.validateBlank(xn702900Req.getAccountNumber());
    }

}
