package com.child.programming.base.mapper;

import com.child.programming.education.manage.dto.PaymentRecordInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
@Repository
public interface PaymentRecordCustomMapper {

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

}
