package com.child.programming.base.mapper;

import com.child.programming.base.model.TbCourseDo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseCustomMapper {

    List<TbCourseDo> getClassNowByExample(Map map);


}
