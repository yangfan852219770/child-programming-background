package com.child.programming.base.service;

import com.child.programming.base.model.TbPaymentRecordDo;
import com.child.programming.education.manage.dto.PaymentRecordInfoDto;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取正式课付费记录
     * @param map
     * @return
     */
    List<PaymentRecordInfoDto> getFormalRecord(Map map);

    /**
     * 获取体验课付费记录
     * @param map
     * @return
     */
    List<PaymentRecordInfoDto> getExperienceRecord(Map map);

    /**
     * 所有课程缴费记录
     * @param studentName
     * @return
     */
    List<PaymentRecordInfoDto> getAllRecord(String studentName);
}
