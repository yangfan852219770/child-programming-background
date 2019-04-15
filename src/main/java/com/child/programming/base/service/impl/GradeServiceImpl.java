package com.child.programming.base.service.impl;

import com.child.programming.app.web.dto.WeekendsSchedule;
import com.child.programming.base.dto.GradeInfoDto;
import com.child.programming.base.dto.GradeWeekendsScheduleDto;
import com.child.programming.base.mapper.GradeCustomMapper;
import com.child.programming.base.mapper.TbGradeDoMapper;
import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.base.model.TbGradeDoExample;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.service.IGradeService;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.DateUtil;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.JSONUtil;
import com.child.programming.education.manage.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private TbGradeDoMapper tbGradeDoMapper;
    @Autowired
    private GradeCustomMapper gradeCustomMapper;

    @Autowired
    private ITeacherService iTeacherService;
    @Autowired
    private IClassroomService iClassroomService;

    @Override
    public List<GradeInfoDto> getList(Map map) {
        List<GradeInfoDto> gradeInfoDtoList = gradeCustomMapper.getList(map);
        if (EmptyUtils.listIsNotEmpty(gradeInfoDtoList)) {
            for (GradeInfoDto gradeInfo : gradeInfoDtoList
            ) {
                Integer[] ids = {gradeInfo.getSchoolId(), gradeInfo.getClassroomId()};
                gradeInfo.setSchoolAndClassroomId(Arrays.asList(ids));
            }
            return gradeInfoDtoList;
        }

        return null;
    }

    @Override
    public InitGradeInfoDto initGradeInfo() {
        InitGradeInfoDto initGradeInfoDto = new InitGradeInfoDto();
        initGradeInfoDto.setTeacherInfoList(iTeacherService.getTeacherSelectList());
        initGradeInfoDto.setClassroomDetailInfoList(iClassroomService.getClassroomCascadeSelect());
        return initGradeInfoDto;
    }

    @Override
    public Boolean save(TbGradeDo gradeDo, Integer userId) {
        if (gradeDo == null)
            return false;

        //新增
        if (EmptyUtils.intIsEmpty(gradeDo.getId())) {
            gradeDo.setStatus(Byte.valueOf("1"));
            gradeDo.setCreateId(userId);
            gradeDo.setCreateTime(new Date());
            return tbGradeDoMapper.insert(gradeDo) > 0;
        } else {
            gradeDo.setLastUpdateId(userId);
            gradeDo.setLastUpdateTime(new Date());
            return tbGradeDoMapper.updateByPrimaryKeySelective(gradeDo) > 0;
        }
    }

    @Override
    public List<SelectDto> getGradeInfoSelectList() {
        TbGradeDoExample example = new TbGradeDoExample();
        TbGradeDoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbGradeDo> gradeDoList = tbGradeDoMapper.selectByExample(example);
        if (EmptyUtils.listIsNotEmpty(gradeDoList)) {
            List<SelectDto> selectDtoList = new ArrayList<>();
            for (TbGradeDo grade : gradeDoList
            ) {
                SelectDto selectDto = new SelectDto();

                selectDto.setValue(grade.getId());
                selectDto.setLabel(grade.getName());
                selectDto.setDisabled(false);

                selectDtoList.add(selectDto);
            }
            return selectDtoList;
        }
        return null;
    }

    @Override
    public Integer validateCapacity(Integer classroomId, Integer maxCapacity) {
        if (EmptyUtils.intIsNotEmpty(classroomId) && EmptyUtils.intIsNotEmpty(maxCapacity)) {
            TbClassroomDo classroomDo = iClassroomService.getOneById(classroomId);
            if (null != classroomDo) {
                if (classroomDo.getMaxCapacity() >= maxCapacity)
                    return -1; // 表示可以添加
                if (classroomDo.getMaxCapacity() < maxCapacity)
                    return classroomDo.getMaxCapacity();
            }
        }
        return 0; // 表示参数错误
    }

    @Override
    public TbGradeDo getOneById(Integer gradeId) {
        if (EmptyUtils.intIsNotEmpty(gradeId))
            return tbGradeDoMapper.selectByPrimaryKey(gradeId);
        return null;
    }

    @Override
    public List<TbGradeDo> getListByClassroomId(Integer classroomId) {
        if (EmptyUtils.intIsNotEmpty(classroomId)) {
            TbGradeDoExample example = new TbGradeDoExample();
            TbGradeDoExample.Criteria criteria = example.createCriteria();
            criteria.andClassroomIdEqualTo(classroomId).andStatusEqualTo(Byte.valueOf("1"));
            return tbGradeDoMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public List<TbGradeDo> getListByTeacherId(Integer teacherId) {
        if (EmptyUtils.intIsNotEmpty(teacherId)) {
            TbGradeDoExample example = new TbGradeDoExample();
            TbGradeDoExample.Criteria criteria = example.createCriteria();
            criteria.andTeacherIdEqualTo(teacherId).andStatusEqualTo(Byte.valueOf("1"));
            return tbGradeDoMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public String validateTimeScheduleConflict(List<CourseTimeScheduleDto> timeSchedule) {
        if (EmptyUtils.listIsNotEmpty(timeSchedule)){
            for (CourseTimeScheduleDto courseTimeSchedule : timeSchedule
            ) {
                TbGradeDo gradeDo = getOneById(courseTimeSchedule.getGradeId());
                // 未查到班级，直接跳过后面的验证，一般不会出现这种情况
                if (EmptyUtils.objectIsEmpty(gradeDo))
                    continue;
                // 获取老师安排
                List<TbGradeDo> teacherGradeList = getListByTeacherId(gradeDo.getTeacherId());
                // 获取教室安排
                List<TbGradeDo> classroomGradeList = getListByClassroomId(gradeDo.getClassroomId());

                //两个集合都是空，说明无冲突
                if (EmptyUtils.listIsEmpty(teacherGradeList) && EmptyUtils.listIsEmpty(classroomGradeList))
                    return "0";

                //对象转换
                List<ValidateTimeScheduleDto> teacherTimeScheduleList = gradeListToValidateTimeSchduleList(teacherGradeList);
                List<ValidateTimeScheduleDto> classroomTimeScheduleList = gradeListToValidateTimeSchduleList(classroomGradeList);
                ValidateTimeScheduleDto validateTimeScheduleDto = courseTimeScheduleToValidateTimeSchduleList(courseTimeSchedule);

                if (EmptyUtils.listIsNotEmpty(teacherTimeScheduleList)
                        && EmptyUtils.listIsNotEmpty(classroomTimeScheduleList)
                        && !EmptyUtils.objectIsEmpty(validateTimeScheduleDto)) {

                    // 老师时间校验
                    String validateTeacherResult = detectTimeScheduleConflict(teacherTimeScheduleList, validateTimeScheduleDto);
                    // 老师时间安排有冲突
                    if (!"0".equals(validateTeacherResult)){
                        // TODO 查询老师信息
                        return validateTeacherResult;
                    }

                    // 教室时间校验
                    String validateClassroomResult = detectTimeScheduleConflict(classroomTimeScheduleList, validateTimeScheduleDto);
                    if (!"0".equals(validateClassroomResult)){
                        // TODO 查询教授信息
                        return validateClassroomResult;
                    }

                } else
                    return "时间转换失败，无法进行校验!";
            }
            // 执行到此处，无冲突
            return "0";
        }
        // 此处return不会执行
        return "-1";
    }

    /**
     * 将TbGradeDo中要校验的时间进行对象转换
     *
     * @param gradeDoList
     * @return
     */
    private List<ValidateTimeScheduleDto> gradeListToValidateTimeSchduleList(List<TbGradeDo> gradeDoList) {
        if (EmptyUtils.listIsNotEmpty(gradeDoList)) {
            List<ValidateTimeScheduleDto> validateTimeScheduleDtoList = new ArrayList<>();
            for (TbGradeDo grade : gradeDoList
            ) {
                ValidateTimeScheduleDto validateTimeScheduleDto = new ValidateTimeScheduleDto();

                validateTimeScheduleDto.setTeacherId(grade.getTeacherId());
                validateTimeScheduleDto.setClassroomId(grade.getClassroomId());
                validateTimeScheduleDto.setStartDate(grade.getStartDate());
                validateTimeScheduleDto.setEndDate(grade.getEndDate());
                validateTimeScheduleDto.setWeekendsScheduleDtoList(JSONUtil.parseArray(grade.getWeekendsSchedule(), GradeWeekendsScheduleDto.class));

                validateTimeScheduleDtoList.add(validateTimeScheduleDto);
            }
            return validateTimeScheduleDtoList;
        }
        return null;
    }

    /**
     * 将CourseTimeScheduleDto 校验的时间进行转换
     *
     * @param courseTimeScheduleDto
     * @return
     */
    private ValidateTimeScheduleDto courseTimeScheduleToValidateTimeSchduleList(CourseTimeScheduleDto courseTimeScheduleDto) {
        if (!EmptyUtils.objectIsEmpty(courseTimeScheduleDto)) {
            ValidateTimeScheduleDto validateTimeScheduleDto = new ValidateTimeScheduleDto();

            validateTimeScheduleDto.setStartDate(DateUtil.stringToDateByDefaultDayFormat(courseTimeScheduleDto.getDateRange().getStartDate()));
            validateTimeScheduleDto.setEndDate(DateUtil.stringToDateByDefaultDayFormat(courseTimeScheduleDto.getDateRange().getEndDate()));
            // 一周时间安排
            List<GradeWeekendsScheduleDto> weekendsScheduleDtoList = new ArrayList<>();
            for (TimeScheduleChildrenDto timeScheduleChildren : courseTimeScheduleDto.getChildrenData()
            ) {
                GradeWeekendsScheduleDto gradeWeekendsScheduleDto = new GradeWeekendsScheduleDto();

                gradeWeekendsScheduleDto.setDay(timeScheduleChildren.getDay());
                gradeWeekendsScheduleDto.setStartHour(timeScheduleChildren.getTimeRange().getStartHour());
                gradeWeekendsScheduleDto.setEndHour(timeScheduleChildren.getTimeRange().getEndHour());

                weekendsScheduleDtoList.add(gradeWeekendsScheduleDto);
            }
            validateTimeScheduleDto.setWeekendsScheduleDtoList(weekendsScheduleDtoList);
            return validateTimeScheduleDto;
        }
        return null;
    }

    /**
     * 冲突检测
     *
     * @param sourceTimeScheduleList 已经安排的时间表
     * @param targetValidateTime     要校验的时间表
     * @return
     */
    private String detectTimeScheduleConflict(List<ValidateTimeScheduleDto> sourceTimeScheduleList, ValidateTimeScheduleDto targetValidateTime) {
        if (EmptyUtils.listIsNotEmpty(sourceTimeScheduleList) && !EmptyUtils.objectIsEmpty(targetValidateTime)) {
            for (ValidateTimeScheduleDto sourceValidateTime : sourceTimeScheduleList
            ) {
                // 两个时间段无交叉，则不需要校验
                if (DateUtil.compareDate(targetValidateTime.getEndDate(), sourceValidateTime.getStartDate()) == 1
                        || DateUtil.compareDate(targetValidateTime.getStartDate(), sourceValidateTime.getEndDate()) == 1)
                    continue;
                // 有交叉，进行校验
                String result = sourceValidateTime.detectConflict(targetValidateTime);
                // 有冲突，返回冲突信息
                if (!"0".equals(result))
                    return result;
            }
            // 执行到此处，说明无冲突
            return "0";
        }
        // 不会执行
        return "-1";
    }
}
