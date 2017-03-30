/**
 * @Title XN802530.java 
 * @Package com.std.account.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年3月30日 下午2:15:48 
 * @version V1.0   
 */
package com.std.account.api.impl;

import com.std.account.ao.IWeChatAO;
import com.std.account.api.AProcessor;
import com.std.account.common.JsonUtil;
import com.std.account.core.OrderNoGenerater;
import com.std.account.core.StringValidater;
import com.std.account.dto.req.XN802530Req;
import com.std.account.enums.EBizType;
import com.std.account.enums.ECurrency;
import com.std.account.exception.BizException;
import com.std.account.exception.ParaException;
import com.std.account.spring.SpringContextHolder;

/** 
 * 虚拟币售卖
 * @author: haiqingzheng 
 * @since: 2017年3月30日 下午2:15:48 
 * @history:
 */
public class XN802530 extends AProcessor {
    private IWeChatAO weChatAO = SpringContextHolder.getBean(IWeChatAO.class);

    private XN802530Req req = null;

    /** 
     * @see com.std.account.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Long amount = StringValidater.toLong(req.getAmount());
        String bizType = null;
        String fromBizNote = null;
        String toBizNote = null;
        if (ECurrency.CGB.getCode().equals(req.getCurrency())) {
            bizType = EBizType.AJ_CGBSM.getCode();
            fromBizNote = "采购币购买";
            toBizNote = "菜狗币售卖";
            amount = amount * 1; // todo根据汇率金额换算，目前1：1
        } else {
            throw new BizException("xn000000", "未知别的币种");
        }
        return weChatAO.getPrepayIdNative(req.getFromUserId(),
            req.getToUserId(), bizType, fromBizNote, toBizNote, amount,
            OrderNoGenerater.generate("PG"));
    }

    /** 
     * @see com.std.account.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802530Req.class);
        StringValidater.validateBlank(req.getFromUserId(), req.getToUserId(),
            req.getAmount(), req.getCurrency());
    }

}
