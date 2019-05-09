package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbPaymentRecordDoMapper;
import com.child.programming.base.model.TbPaymentRecordDo;
import com.child.programming.base.service.IPaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class PaymentRecordServiceImpl implements IPaymentRecordService {
    @Autowired
    private TbPaymentRecordDoMapper paymentRecordDoMapper;

    @Override
    public Boolean insert(TbPaymentRecordDo paymentRecordDo, Integer userId) {
        if (null == paymentRecordDo || null == userId)
            return false;
        paymentRecordDo.setPayTime(new Date());
        paymentRecordDo.setCreateId(userId);
        paymentRecordDo.setCreateTime(new Date());
        paymentRecordDo.setStatus(Byte.valueOf("1"));
        return paymentRecordDoMapper.insert(paymentRecordDo) > 0;
    }
}
