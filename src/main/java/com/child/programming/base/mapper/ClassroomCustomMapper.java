package com.child.programming.base.mapper;

import com.child.programming.education.manage.dto.ClassroomInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/

@Repository
public interface ClassroomCustomMapper {

    /**
     * 直接获取教室信息 或者根据校区id
     * @param params
     * @return
     */
    List<ClassroomInfoDto> getListOrBySchoolId(Map<String, Integer> params);

}
