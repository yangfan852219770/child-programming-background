package com.child.programming.base.mapper;

import com.child.programming.base.dto.GradeInfoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
@Repository
public interface GradeCustomMapper {

    /**
     * 班级列表
     * @param map
     * @return
     */
    List<GradeInfoDto> getList(Map map);
}
