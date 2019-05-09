package com.child.programming.base.service;

import com.child.programming.base.model.TbPaymentRecordDo;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface IPaymentRecordService {

    /**
     * 付费新增
     * @param paymentRecordDo
     * @param userId
     * @return
     */
    Boolean insert(TbPaymentRecordDo paymentRecordDo, Integer userId);
}
