package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.StudentCourseScheduleCustomMapper;
import com.child.programming.base.mapper.TbStudentCourseScheduleDoMapper;
import com.child.programming.base.model.TbStudentCourseScheduleDo;
import com.child.programming.base.model.TbStudentCourseScheduleDoExample;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.service.ISignUpFormalCourseService;
import com.child.programming.base.service.IStudentCourseScheduleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.education.manage.dto.CourseDetailDto;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import com.child.programming.education.manage.dto.StudentScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class StudentCourseScheduleServiceImpl implements IStudentCourseScheduleService {
    @Autowired
    private TbStudentCourseScheduleDoMapper studentCourseScheduleDoMapper;
    @Autowired
    private StudentCourseScheduleCustomMapper studentCourseScheduleCustomMapper;

    @Autowired
    private ISignUpFormalCourseService iSignUpFormalCourseService;
    @Autowired
    private ICourseService iCourseService;

    // TODO 批量操作，事务处理
    @Override
    public Boolean generateBatchSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer gradeId, Integer userId) {
        if (EmptyUtils.listIsEmpty(courseScheduleDtoList) || EmptyUtils.intIsEmpty(gradeId))
            return false;
        // 缴费、不是中途报名的学生
        // 无人报名，则不必生成课表
        List<TbStudentSignUpDo> studentSignUpDoList = iSignUpFormalCourseService.getListByWhere(gradeId, Byte.valueOf("1"), Byte.valueOf("0"));
        if (EmptyUtils.listIsEmpty(studentSignUpDoList))
            return true;
        List<TbStudentCourseScheduleDo> studentCourseScheduleDoList = new ArrayList<>();
        // TODO 之后分步插入，打印日志
        for (TbStudentSignUpDo studentSignUp:studentSignUpDoList
             ) {
            List<TbStudentCourseScheduleDo> studentCourseScheduleDoList1 = ListUtil.convertElement(courseScheduleDtoList, TbStudentCourseScheduleDo.class);
            if (EmptyUtils.listIsEmpty(studentCourseScheduleDoList1))
                return false;
            for (TbStudentCourseScheduleDo schedule:studentCourseScheduleDoList1
                 ) {
                schedule.setCreateId(userId);
                schedule.setCreateTime(new Date());
                schedule.setStatus(Byte.valueOf("1"));
                schedule.setIsSignIn(Byte.valueOf("0"));
                // TODO 签到字段，设置默认值 0
                schedule.setStudentId(studentSignUp.getStudentId());
            }
            studentCourseScheduleDoList.addAll(studentCourseScheduleDoList1);
        }
        int count = studentCourseScheduleCustomMapper.insertBatch(studentCourseScheduleDoList);

        return count == studentCourseScheduleDoList.size();
    }

    @Override
    public Boolean generateOneSchedule(List<CourseScheduleDto> courseScheduleDtoList, Integer studentId, Integer userId) {
        if (EmptyUtils.listIsEmpty(courseScheduleDtoList) || EmptyUtils.intIsEmpty(studentId))
            return false;
        List<TbStudentCourseScheduleDo> studentCourseScheduleDoList = ListUtil.convertElement(courseScheduleDtoList, TbStudentCourseScheduleDo.class);
        if (EmptyUtils.listIsEmpty(studentCourseScheduleDoList))
            return false;
        for (TbStudentCourseScheduleDo schedule:studentCourseScheduleDoList
        ) {
            schedule.setCreateId(userId);
            schedule.setCreateTime(new Date());
            schedule.setStatus(Byte.valueOf("1"));
            schedule.setIsSignIn(Byte.valueOf("0"));
            // TODO 签到字段，设置默认值 0
            schedule.setStudentId(studentId);
        }
        int count = studentCourseScheduleCustomMapper.insertBatch(studentCourseScheduleDoList);
        return count == studentCourseScheduleDoList.size();
    }

    @Override
    public List<StudentScheduleDto> getStudentCourseScheduleList(Integer studentId) {
        if (EmptyUtils.intIsEmpty(studentId))
            return null;
        TbStudentCourseScheduleDoExample example = new TbStudentCourseScheduleDoExample();
        example.setOrderByClause("period");
        TbStudentCourseScheduleDoExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        List<TbStudentCourseScheduleDo> studentCourseScheduleDoList = studentCourseScheduleDoMapper.selectByExample(example);
        if (EmptyUtils.listIsEmpty(studentCourseScheduleDoList))
            return null;

        // 将gradeId 放入set中
        Set<Integer> gradeIdSet = new LinkedHashSet<>();
        for (TbStudentCourseScheduleDo s:studentCourseScheduleDoList
             ) {
            if (null != s.getGradeId())
                gradeIdSet.add(s.getGradeId());
            if (null != s.getTempGradeId())
                gradeIdSet.add(s.getTempGradeId());
        }
        if (gradeIdSet.isEmpty())
            return null;
        // 根据gradeId 查询相关校区、教室、课程信息
        List<CourseDetailDto> courseDetailDtoList = iCourseService.getCourseDetaiListByGradeIdSet(gradeIdSet);
        if (EmptyUtils.listIsEmpty(courseDetailDtoList))
            return null;

        List<StudentScheduleDto> studentScheduleDtoList = new ArrayList<>();
        for (TbStudentCourseScheduleDo s:studentCourseScheduleDoList
             ) {
            for (CourseDetailDto c:courseDetailDtoList
                 ) {
                // TODO 调课，需重新查询调课后的课表
                if (Byte.valueOf("1").equals(s.getIsAdjust()) && s.getTempGradeId().equals(c.getGradeId())){
                    StudentScheduleDto studentScheduleDto = new StudentScheduleDto();

                    studentScheduleDto.setStudentCourseSchedule(s);
                    studentScheduleDto.setCourseDetail(c);

                    studentScheduleDtoList.add(studentScheduleDto);
                    break;
                } else if (s.getGradeId().equals(c.getGradeId())){
                    StudentScheduleDto studentScheduleDto = new StudentScheduleDto();

                    studentScheduleDto.setStudentCourseSchedule(s);
                    studentScheduleDto.setCourseDetail(c);

                    studentScheduleDtoList.add(studentScheduleDto);
                    break;
                }

            }
        }
        return studentScheduleDtoList;
    }
}
