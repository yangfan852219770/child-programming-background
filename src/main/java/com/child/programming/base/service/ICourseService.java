package com.child.programming.base.service;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.app.web.dto.HomePageHeighSerachParam;
import com.child.programming.app.web.dto.SignUpCourseDto;
import com.child.programming.base.dto.CourseInfoDto;
import com.child.programming.base.model.TbCourseDo;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.education.manage.dto.CourseSaveDto;
import com.child.programming.education.manage.dto.CourseTimeScheduleDto;

import java.util.List;

public interface ICourseService {
    /**
     * @Description:    小程序首页课程列表展示，包括搜索，高级搜索
     * @param homePageHeighSerachParam 查询的参数
     */
    List<TbCourseDo> getClassNow(HomePageHeighSerachParam homePageHeighSerachParam);

    /**
     * @Description:    根据课程id查询小程序课程安排详细信息
     */
    List<CourseArrange> getCourseDetailByCourseId(int courseId);


    /**
     * @Description:    根据学生ID查询报名的课程
     */
    List<SignUpCourseDto> getStudentSignUpCourseList(int page, int limit, String studentId);

    /**
     * @Description:    根据课程ID查询课程信息
     */
    TbCourseDo getCourseById(int courseId);

    /**
     * @Description:    查询学生的购课历史
     */
    List<SignUpCourseDto> getStudentSignUpCourseHistoryList(int page, int limit, String studentId);

    /**
     * @Description:    查询学生的正在进行的课程信息
     */
    List<SignUpCourseDto> getStudentCourseClassList(int page, int limit, String studentId);

    /**
     * @Description:    根据日期查询学生的课程信息列表
     */
    List<SignUpCourseDto> getStudentCourseListByDate(String selectDate, String week, String studentId);

    /**
     *  @Description:    查询学生收藏课程
     */
    List<TbCourseDo> getStudentCollectCourseList(int page, int limit, int studentId);

    /**
     * antd 列表
     * @param name 课程名称
     * @return
     */
    List<CourseSaveDto> getList(String name);

    /**
     * 新增、编辑
     * @param userId
     * @param courseTimeScheduleDtoList
     * @param courseDo
     * @return
     */
    Boolean save(Integer userId, TbCourseDo courseDo, List<CourseTimeScheduleDto> courseTimeScheduleDtoList);

    /**
     * 更新
     * @param userId
     * @param courseDo
     * @return
     */
    Boolean updateCourse(Integer userId, TbCourseDo courseDo);

    /**
     * 生成老师、学生课表
     * @param courseId
     * @param userId
     * @return
     */
    Boolean generateCourseSchedule(Integer courseId, Integer userId);

    CourseArrange getCourseDetailByGradeId(int gradeId);
}
