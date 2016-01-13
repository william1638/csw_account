package com.std.account.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.account.ao.IDictAO;
import com.std.account.bo.IDictBO;
import com.std.account.bo.base.Paginable;
import com.std.account.domain.Dict;
import com.std.account.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2015年9月10日 上午8:51:03 
 * @history:
 */
@Service
public class DictAOImpl implements IDictAO {
    @Autowired
    IDictBO dictBO;

    @Override
    public Paginable<Dict> queryDictPage(int start, int limit, Dict condition) {
        return dictBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Dict> queryDictList(Dict condition) {
        return dictBO.queryDictList(condition);
    }

    @Override
    public int addDict(Long pId, String key, String value, String creator,
            String remark) {
        if (pId > 0) {
            if (!dictBO.isDictExist(pId)) {
                throw new BizException("ZC000001", "数据字典父节点序号不存在");
            }
        }
        Dict data = new Dict();
        data.setpId(pId);
        data.setKey(key);
        data.setValue(value);
        data.setCreator(creator);
        data.setRemark(remark);
        return dictBO.saveDict(data);
    }

    @Override
    public int dropDict(Long id) {
        int count = 0;
        if (id > 0) {
            if (!dictBO.isDictExist(id)) {
                throw new BizException("ZC000001", "数据字典序号不存在");
            }
            count = dictBO.removeDict(id);
        }
        return count;
    }

    @Override
    public int editDict(Long id, Long pId, String key, String value,
            String updater, String remark) {
        if (!dictBO.isDictExist(id)) {
            throw new BizException("ZC000001", "数据字典节点序号不存在");
        }
        if (!dictBO.isDictExist(pId)) {
            throw new BizException("ZC000001", "数据字典父节点序号不存在");
        }
        Dict data = new Dict();
        data.setId(id);
        data.setpId(pId);
        data.setKey(key);
        data.setValue(value);
        data.setUpdater(updater);
        data.setRemark(remark);
        return dictBO.refreshDict(data);
    }
}
