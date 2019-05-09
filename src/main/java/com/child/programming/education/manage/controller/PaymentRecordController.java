package com.child.programming.education.manage.controller;

import com.child.programming.base.service.IPaymentRecordService;
import com.child.programming.education.manage.dto.PaymentRecordInfoDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description：缴费记录管理
 * @Author：yangfan
 **/

@RestController
@RequestMapping("/paymentRecord")
@Log4j2
public class PaymentRecordController {

    @Autowired
    private IPaymentRecordService iPaymentRecordService;

    /**
     * 付费记录列表
     * @param studentName
     * @return
     */
    @RequestMapping(value = "getAllRecordList")
    public List<PaymentRecordInfoDto> getAllRecordList(@RequestParam(value = "studentName", required = false)String studentName) {
        return iPaymentRecordService.getAllRecord(studentName);
    }
}
