package com.child.programming.base.mapper;

import com.child.programming.base.dto.ClassroomDetailInfoDto;
import com.child.programming.education.manage.dto.ValidateClassroomInfoDto;
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
     * @param map
     * @return
     */
    List<ClassroomDetailInfoDto> getListOrBySchoolId(Map<String, Integer> map);

    /**
     * 删除学校时，校验是否还有教室占用
     * @param map
     * @return
     */
    List<ValidateClassroomInfoDto> getValidateClassroomInfoListBySchoolId(Map<String, List<Integer>> map);
}
