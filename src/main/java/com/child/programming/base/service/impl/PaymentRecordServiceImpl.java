package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.PaymentRecordCustomMapper;
import com.child.programming.base.mapper.TbPaymentRecordDoMapper;
import com.child.programming.base.model.TbPaymentRecordDo;
import com.child.programming.base.service.IPaymentRecordService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.education.manage.dto.PaymentRecordInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class PaymentRecordServiceImpl implements IPaymentRecordService {
    @Autowired
    private TbPaymentRecordDoMapper paymentRecordDoMapper;

    @Autowired
    private PaymentRecordCustomMapper paymentRecordCustomMapper;

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

    @Override
    public List<PaymentRecordInfoDto> getFormalRecord(Map map) {
        return paymentRecordCustomMapper.getFormalRecord(map);
    }

    @Override
    public List<PaymentRecordInfoDto> getExperienceRecord(Map map) {
        return paymentRecordCustomMapper.getExperienceRecord(map);
    }

    @Override
    public List<PaymentRecordInfoDto> getAllRecord(String studentName) {
        Map map = new HashMap();
        if (EmptyUtils.stringIsEmpty(studentName))
            map.put("studentName", studentName);
        else
            map.put("studentName", "%" + studentName + "%");
        // 正式课
        List<PaymentRecordInfoDto> formalRecordList = getFormalRecord(map);
        // 体验课
        List<PaymentRecordInfoDto> experienceRecordList = getExperienceRecord(map);

        List<PaymentRecordInfoDto> allRecordList = new ArrayList<>();
        if (EmptyUtils.listIsNotEmpty(formalRecordList))
            allRecordList.addAll(formalRecordList);
        if (EmptyUtils.listIsNotEmpty(experienceRecordList))
            allRecordList.addAll(experienceRecordList);
        return allRecordList;
    }
}
