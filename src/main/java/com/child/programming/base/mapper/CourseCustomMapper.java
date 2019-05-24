package com.child.programming.base.mapper;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.app.web.dto.SignUpCourseDto;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.education.manage.dto.CourseDetailDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface CourseCustomMapper {

    List<TbCourseDo> getClassNowByExample(Map map);


    List<CourseArrange> getCourseDetailByCourseId(int courseId);

    List<SignUpCourseDto> getStudentSignUpCourseList(Map map);

    List<SignUpCourseDto> getStudentSignUpCourseHistoryList(Map map);

    List<SignUpCourseDto> getStudentCourseClassList(Map map);

    List<SignUpCourseDto> getStudentCourseListByDate(Map map);

    List<TbCourseDo> getStudentCollectCourseList(Map map);

    CourseArrange getCourseDetailByGradeId(int gradeId);

    List<CourseDetailDto> getCourseDetaiListByGradeIdSet(Set gradeIdSet);
}
