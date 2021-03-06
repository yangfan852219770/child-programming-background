package com.child.programming.base.mapper;

import com.child.programming.base.dto.SignUpExperienceCourseInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
@Repository
public interface SignUpExperienceCourseCustomMapper {

    List<SignUpExperienceCourseInfoDto> getList(Map map);
}
