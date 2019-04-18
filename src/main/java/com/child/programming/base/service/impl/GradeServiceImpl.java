package com.child.programming.base.service.impl;

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
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.DateUtil;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.JSONUtil;
import com.child.programming.education.manage.dto.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
@Log4j2
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
    public List<SelectDto> getGradeInfoSelectList(String gradeIds) {
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
                // 将已用课程的班级禁选
                if (EmptyUtils.intIsNotEmpty(grade.getCourseId()))
                    selectDto.setDisabled(true);
                selectDtoList.add(selectDto);
            }
            // 已选的班级禁用
            if (EmptyUtils.stringIsNotEmpty(gradeIds)){
                String[] gradeIdArray = gradeIds.split(",");
                if (!EmptyUtils.objectIsEmpty(gradeIdArray)){
                    for (SelectDto select:selectDtoList
                         ) {
                        for (String gradeId:gradeIdArray
                             ) {
                            if (select.getValue().equals(Integer.parseInt(gradeId))){
                                select.setDisabled(true);
                            }
                        }
                    }
                }
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

    // TODO 目前只对数据库存在的数据校验，并未对提交的数据校验
    @Override
    public String validateTimeScheduleConflict(List<CourseTimeScheduleDto> timeSchedule, Integer courseId) {
        if (EmptyUtils.listIsNotEmpty(timeSchedule)) {
            for (CourseTimeScheduleDto courseTimeSchedule : timeSchedule
            ) {
                TbGradeDo gradeDo = getOneById(courseTimeSchedule.getGradeId());

                // 有课的不能再安排课程
                // TODO 后期不在此处校验
                if (EmptyUtils.intIsNotEmpty(gradeDo.getCourseId()))
                    if (!gradeDo.getCourseId().equals(courseId))
                        return gradeDo.getName() + "已经安排课程，不能重复安排!";
                // 未查到班级，直接跳过后面的验证，只有第一次新增会出现
                if (EmptyUtils.objectIsEmpty(gradeDo))
                    continue;

                // 获取老师安排
                List<TbGradeDo> teacherGradeList = getListByTeacherId(gradeDo.getTeacherId());
                // 获取教室安排
                List<TbGradeDo> classroomGradeList = getListByClassroomId(gradeDo.getClassroomId());

                // 移除当前要校验的元素
                teacherGradeList = removeOneByGradeId(teacherGradeList, gradeDo.getId());
                classroomGradeList = removeOneByGradeId(classroomGradeList, gradeDo.getId());

                // 两个集合都是空，说明无冲突
                if (EmptyUtils.listIsEmpty(teacherGradeList) && EmptyUtils.listIsEmpty(classroomGradeList))
                    return "0";

                // 对象转换
                List<ValidateTimeScheduleDto> teacherTimeScheduleList = gradeListToValidateTimeSchduleList(teacherGradeList);
                List<ValidateTimeScheduleDto> classroomTimeScheduleList = gradeListToValidateTimeSchduleList(classroomGradeList);
                ValidateTimeScheduleDto validateTimeScheduleDto = courseTimeScheduleToValidateTimeSchduleList(courseTimeSchedule);

                //校验数据不合法
                if (EmptyUtils.objectIsEmpty(validateTimeScheduleDto)){
                    log.warn(ConstDataUtil.VALIDATE_PARAMETER_FALSE);
                    return ConstDataUtil.VALIDATE_PARAMETER_FALSE;
                }

                //teacherTimeScheduleList、classroomTimeScheduleList无校验数据的直接通过
                // 老师时间校验
                String validateTeacherResult = detectTimeScheduleConflict(teacherTimeScheduleList, validateTimeScheduleDto);
                // 老师时间安排有冲突
                if (!"0".equals(validateTeacherResult)) {
                    // TODO 查询老师信息
                    return "该老师" + validateTeacherResult;
                }

                // 教室时间校验
                String validateClassroomResult = detectTimeScheduleConflict(classroomTimeScheduleList, validateTimeScheduleDto);
                if (!"0".equals(validateClassroomResult)) {
                    // TODO 查询教室信息
                    return "该教室" + validateClassroomResult;
                }
            }
            // 执行到此处，校验通过
            return "0";
        }
        log.warn(ConstDataUtil.VALIDATE_PARAMETER_FALSE);
        return ConstDataUtil.VALIDATE_PARAMETER_FALSE;
    }

    // TODO 批量操作，未有事务回滚
    @Override
    public Boolean updateTimeSchedule(Integer courseId, Integer userId, List<CourseTimeScheduleDto> courseTimeScheduleDtoList) {

        // 将此次不包含班级id的时间安排置空
        List<TbGradeDo> gradeDoList = getListByCourseId(courseId);
        if (EmptyUtils.listIsNotEmpty(gradeDoList)){
            List<TbGradeDo> resetGradeList = new ArrayList<>();
            // 找出此次不包含的班级id
            for (TbGradeDo grade:gradeDoList
                 ) {
                boolean flag = false; // 默认不存在
                for (CourseTimeScheduleDto schedule:courseTimeScheduleDtoList
                     ) {
                    // 如果存在，则终止循环
                    if (grade.getId().equals(schedule.getGradeId())){
                        flag = true;
                        break;
                    }
                }
                // 不存在，则需置空
                if (!flag)
                    resetGradeList.add(grade);
            }

            // 置空处理
            if (EmptyUtils.listIsNotEmpty(resetGradeList)){
                int resetCount = 0;
                for (TbGradeDo grade:resetGradeList
                     ) {
                    // TODO 此处set方法可提出去
                    grade.setCourseId(null);
                    grade.setStartDate(null);
                    grade.setEndDate(null);
                    grade.setWeekendsSchedule(null);
                    grade.setLastUpdateId(userId);
                    grade.setLastUpdateTime(new Date());
                    // TODO 之后此处更新改为sql
                    resetCount += tbGradeDoMapper.updateByPrimaryKey(grade);
                }
                if (resetCount != resetGradeList.size())
                    return false;
            }
        }

        // 将涉及到班级的时间安排更新
        int count = 0;
        TbGradeDo gradeDo = new TbGradeDo();
        for (CourseTimeScheduleDto timeSchedule : courseTimeScheduleDtoList
        ) {
            gradeDo.setId(timeSchedule.getGradeId());
            gradeDo.setCourseId(courseId);
            // 时间安排
            gradeDo.setStartDate(DateUtil.stringToDateByDefaultDayFormat(timeSchedule.getDateRange().getStartDate()));
            gradeDo.setEndDate(DateUtil.stringToDateByDefaultDayFormat(timeSchedule.getDateRange().getEndDate()));
            if (EmptyUtils.listIsEmpty(timeSchedule.getChildrenData()))
                return false;
            List<GradeWeekendsScheduleDto> gradeWeekendsScheduleDtoList = timeSchedule.convertToGradeWeekendsSchedule();
            if (EmptyUtils.listIsEmpty(gradeWeekendsScheduleDtoList))
                return false;
            gradeDo.setWeekendsSchedule(JSONUtil.toJSONString(gradeWeekendsScheduleDtoList));

            gradeDo.setLastUpdateTime(new Date());
            gradeDo.setLastUpdateId(userId);

            count += tbGradeDoMapper.updateByPrimaryKeySelective(gradeDo);
        }
        return count == courseTimeScheduleDtoList.size();
    }

    @Override
    public List<TbGradeDo> getListByCourseId(Integer courseId) {
        if (EmptyUtils.intIsNotEmpty(courseId)){
            TbGradeDoExample example = new TbGradeDoExample();
            TbGradeDoExample.Criteria criteria = example.createCriteria();
            criteria.andCourseIdEqualTo(courseId).andStatusEqualTo(Byte.valueOf("1"));
            return tbGradeDoMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public CourseTimeScheduleDto convertToCourseTimeSchedule(TbGradeDo gradeDo) {
        //TODO 此处应加上对gradeDo为空的判断
        CourseTimeScheduleDto courseTimeScheduleDto = new CourseTimeScheduleDto();

        // 班级信息
        LabelInValueDto labelInValueDto = new LabelInValueDto();
        labelInValueDto.setKey(gradeDo.getId());
        labelInValueDto.setLabel(gradeDo.getName());
        courseTimeScheduleDto.setGradeSelect(labelInValueDto);

        //日期
        DateRangeDto dateRangeDto = new DateRangeDto();
        dateRangeDto.setStartDate(DateUtil.dateToStringByDefaultDayFormat(gradeDo.getStartDate()));
        dateRangeDto.setEndDate(DateUtil.dateToStringByDefaultDayFormat(gradeDo.getEndDate()));

        //一周安排
        if (EmptyUtils.stringIsNotEmpty(gradeDo.getWeekendsSchedule())){

            List<GradeWeekendsScheduleDto> gradeWeekendsScheduleDtoList = JSONUtil.parseArray(gradeDo.getWeekendsSchedule(), GradeWeekendsScheduleDto.class);
            if (EmptyUtils.listIsNotEmpty(gradeWeekendsScheduleDtoList)){
                List<TimeScheduleChildrenDto> timeScheduleChildrenDtoList = new ArrayList<>();
                for (GradeWeekendsScheduleDto gradeWeekendsSchedule:gradeWeekendsScheduleDtoList
                ) {
                    TimeScheduleChildrenDto timeScheduleChildrenDto = gradeWeekendsSchedule.convertToTimeScheduleChildren();
                    timeScheduleChildrenDtoList.add(timeScheduleChildrenDto);
                }
                courseTimeScheduleDto.setChildrenData(timeScheduleChildrenDtoList);
            }
        }
        courseTimeScheduleDto.setGradeId(gradeDo.getId());
        courseTimeScheduleDto.setDateRange(dateRangeDto);
        return courseTimeScheduleDto;
    }

    @Override
    public Boolean validateCourseId(String[] idArray) {
        if(EmptyUtils.arrayIsEmpty(idArray))
            return false;
        for (String id:idArray
             ) {
            TbGradeDo gradeDo = getOneById(Integer.parseInt(id));
            if (!EmptyUtils.objectIsEmpty(gradeDo))
                if (EmptyUtils.intIsNotEmpty(gradeDo.getCourseId()))
                    return false;
        }
        return true;
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
        ) {
            TbGradeDo gradeDo = tbGradeDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != gradeDo){
                gradeDo.setStatus(Byte.valueOf("0"));
                gradeDo.setLastUpdateId(userId);
                gradeDo.setLastUpdateTime(new Date());
                result += tbGradeDoMapper.updateByPrimaryKeySelective(gradeDo);
            }
        }

        return result == idArray.length;
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
                // 时间安排为空，则不必放入校验集合
                if (EmptyUtils.stringIsEmpty(grade.getWeekendsSchedule()))
                    continue;
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
            if (EmptyUtils.listIsEmpty(courseTimeScheduleDto.getChildrenData()))
                return null;
            List<GradeWeekendsScheduleDto> weekendsScheduleDtoList = courseTimeScheduleDto.convertToGradeWeekendsSchedule();
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
        //无要校验的数据直接通过
        if (EmptyUtils.listIsEmpty(sourceTimeScheduleList))
            return "0";

        if (!EmptyUtils.objectIsEmpty(targetValidateTime)) {
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
        log.warn(ConstDataUtil.VALIDATE_PARAMETER_FALSE);
        return ConstDataUtil.VALIDATE_PARAMETER_FALSE;
    }

    /**
     * 此方法只移除集合的一个元素
     * @param gradeDoList
     * @param gradeId
     * @return
     */
    private List<TbGradeDo> removeOneByGradeId(List<TbGradeDo> gradeDoList, Integer gradeId){
        if (EmptyUtils.listIsEmpty(gradeDoList))
            return null;
        for (int i = 0; i < gradeDoList.size(); i++) {
            if (gradeDoList.get(i).getId().equals(gradeId)){
                gradeDoList.remove(i);
                break;
            }
        }
        return gradeDoList;
    }

}
