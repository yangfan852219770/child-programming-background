package com.child.programming.base.service.impl;

import com.child.programming.base.dto.GradeInfoDto;
import com.child.programming.base.dto.GradeWeekendsScheduleDto;
import com.child.programming.base.mapper.GradeCustomMapper;
import com.child.programming.base.mapper.TbGradeDoMapper;
import com.child.programming.base.model.*;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.service.IGradeService;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.*;
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
    @Autowired
    private ICourseService iCourseService;

    // 计算课程表用
    private static final Map<String, Integer> weekMap = new HashMap();

    static {
        weekMap.put("一", 0);
        weekMap.put("二", 1);
        weekMap.put("三", 2);
        weekMap.put("四", 3);
        weekMap.put("五", 4);
        weekMap.put("六", 5);
        weekMap.put("日", 6);
    }

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

    @Override
    public String validateTimeScheduleConflict(List<CourseTimeScheduleDto> timeSchedule, Integer courseId) {
        if (EmptyUtils.listIsNotEmpty(timeSchedule)) {
            for (CourseTimeScheduleDto courseTimeSchedule : timeSchedule
            ) {
                TbGradeDo gradeDo = getOneById(courseTimeSchedule.getGradeId());

                // 未查到班级，直接跳过后面的验证，只有第一次新增会出现
                if (EmptyUtils.objectIsEmpty(gradeDo))
                    continue;

                // 有课的不能再安排课程,防止重复安排课程
                // TODO 后期不在此处校验
                if (EmptyUtils.intIsNotEmpty(gradeDo.getCourseId()))
                    if (!gradeDo.getCourseId().equals(courseId))
                        return gradeDo.getName() + "已经安排课程，不能重复安排!";


                // 代码复用性
                // 获取数据库中老师安排
                List<TbGradeDo> teacherGradeList = getListByTeacherId(gradeDo.getTeacherId());
                // 获取数据库中教室安排
                List<TbGradeDo> classroomGradeList = getListByClassroomId(gradeDo.getClassroomId());

                // 移除当前要校验的元素
                removeOneByGradeId(teacherGradeList, gradeDo.getId());
                removeOneByGradeId(classroomGradeList, gradeDo.getId());

                // 移除已经结课的时间安排
                removeEndCourse(teacherGradeList);
                removeEndCourse(classroomGradeList);
                //TODO 编辑时 查出的班级安排，在提交数据中也出现，则按照提交数据的安排

                // 校验数据中，如果存在一个老师教不同班级的情况，也要放入老师安排数组中
                teacherGradeList = addGradeByTeacherId(teacherGradeList, timeSchedule, gradeDo.getTeacherId(), gradeDo.getId());
                // 教室同理
                classroomGradeList = addGradeByClassroomId(classroomGradeList, timeSchedule, gradeDo.getClassroomId(), gradeDo.getId());

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

                    return gradeDo.getName() + "与" + validateTeacherResult;
                }

                // 教室时间校验
                String validateClassroomResult = detectTimeScheduleConflict(classroomTimeScheduleList, validateTimeScheduleDto);
                if (!"0".equals(validateClassroomResult)) {

                    return gradeDo.getName() + "与" + validateClassroomResult;
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

        for (CourseTimeScheduleDto timeSchedule : courseTimeScheduleDtoList
        ) {
            TbGradeDo gradeDo = converCourseTimeScheduleDtoToTbGradeDo(timeSchedule);
            if (EmptyUtils.objectIsEmpty(gradeDo))
                return false;

            gradeDo.setCourseId(courseId);
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

    @Override
    public List<CourseScheduleDto> convertToCourseSchedule(TbGradeDo gradeDo) {
        // 参数校验
        if (null == gradeDo || EmptyUtils.stringIsEmpty(gradeDo.getWeekendsSchedule()))
            return null;
        TbCourseDo courseDo = iCourseService.getCourseById(gradeDo.getCourseId());
        if (null == courseDo)
            return null;
        // 时间安排集合
        List<GradeWeekendsScheduleDto> gradeWeekendsScheduleDtoList = JSONUtil.parseArray(gradeDo.getWeekendsSchedule(), GradeWeekendsScheduleDto.class);
        if (EmptyUtils.listIsEmpty(gradeWeekendsScheduleDtoList))
            return null;

        // 将查出的时间安排，转化为单个元素
        List<WeekendsScheduleDto> weekendsScheduleDtoList = new ArrayList<>();
        for (GradeWeekendsScheduleDto schedule:gradeWeekendsScheduleDtoList
             )
            weekendsScheduleDtoList.addAll(schedule.convertToWeekendsSchedule());

        // 时间安排排序，从小到大
        /*weekendsScheduleDtoList = weekendsScheduleDtoList.stream()
                .sorted(Comparator.comparing(WeekendsScheduleDto::getDay)
                .thenComparing(WeekendsScheduleDto::getStartHour))
                .collect(Collectors.toList());*/
        Collections.sort(weekendsScheduleDtoList, new Comparator<WeekendsScheduleDto>() {
            @Override
            public int compare(WeekendsScheduleDto o1, WeekendsScheduleDto o2) {
                if (weekMap.get(o1.getDay()).compareTo(weekMap.get(o2.getDay())) < 0)
                    return -1;
                if (o1.getStartHour().compareTo(o2.getStartHour()) < 0)
                    return -1;
                return 1;
            }
        });
        // 将时间安排转化为日期表格式的数据
        List<CourseScheduleDto> courseScheduleDtoList = new ArrayList<>();
        // 计算连个日期间隔天数
        int days = DateUtil.computeDiffDays(gradeDo.getStartDate(), gradeDo.getEndDate());
        // 计算有几个星期
        int weekCount = days / 7;
        // 课时
        int period = 0;

        for (int i = 0; i <weekCount; i++) {
            // 开始上课时间，日月年
            Date startDate = DateUtil.add(gradeDo.getStartDate(),Calendar.DATE, i * 7);
            for (WeekendsScheduleDto schedule:weekendsScheduleDtoList
                 ) {
                period ++;
                int addDay = weekMap.get(schedule.getDay());
                Date courseDate = DateUtil.add(startDate, Calendar.DATE, addDay);
                CourseScheduleDto courseScheduleDto = new CourseScheduleDto();

                courseScheduleDto.setGradeId(gradeDo.getId());
                courseScheduleDto.setCourseId(gradeDo.getCourseId());
                courseScheduleDto.setTeacherId(gradeDo.getTeacherId());
                courseScheduleDto.setStartTime(DateUtil.addHHmmss(courseDate, schedule.getStartHour()));
                courseScheduleDto.setEndTime(DateUtil.addHHmmss(courseDate,schedule.getEndHour()));
                courseScheduleDto.setPeriod(period);

                courseScheduleDtoList.add(courseScheduleDto);
            }
        }
        return courseScheduleDtoList;
    }

    @Override
    public List<ValidateDeleteDto> validateByClassroomIds(String[] idArray) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return null;
        List<Integer> classroomIdList = ListUtil.stringArrayToIntegerList(idArray);
        if (EmptyUtils.listIsEmpty(classroomIdList))
            return null;
        TbGradeDoExample example = new TbGradeDoExample();
        TbGradeDoExample.Criteria criteria = example.createCriteria();
        criteria.andClassroomIdIn(classroomIdList).andStatusEqualTo(Byte.valueOf("1"));
        List<TbGradeDo> gradeDoList = tbGradeDoMapper.selectByExample(example);
        if (EmptyUtils.listIsEmpty(gradeDoList))
            return null;
        List<ValidateDeleteDto> gradeDtoList = new ArrayList<>();
        for (TbGradeDo g:gradeDoList
             ) {
            ValidateDeleteDto validateDeleteDto = new ValidateDeleteDto();
            validateDeleteDto.setId(g.getId());
            validateDeleteDto.setName(g.getName());
            gradeDtoList.add(validateDeleteDto);
        }
        return gradeDtoList;
    }

    @Override
    public List<ValidateDeleteDto> validateByTeacherIds(String[] idArray) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return null;
        List<Integer> teacherIdList = ListUtil.stringArrayToIntegerList(idArray);
        if (EmptyUtils.listIsEmpty(teacherIdList))
            return null;
        TbGradeDoExample example = new TbGradeDoExample();
        TbGradeDoExample.Criteria criteria = example.createCriteria();
        criteria.andTeacherIdIn(teacherIdList).andStatusEqualTo(Byte.valueOf("1"));
        List<TbGradeDo> gradeDoList = tbGradeDoMapper.selectByExample(example);
        if (EmptyUtils.listIsEmpty(gradeDoList))
            return null;
        List<ValidateDeleteDto> gradeDtoList = new ArrayList<>();
        for (TbGradeDo g:gradeDoList
        ) {
            ValidateDeleteDto validateDeleteDto = new ValidateDeleteDto();
            validateDeleteDto.setId(g.getId());
            validateDeleteDto.setName(g.getName());
            gradeDtoList.add(validateDeleteDto);
        }
        return gradeDtoList;
    }

    /**
     * 移除已经结课的校验数据
     * @param gradeDoList
     */
    private void removeEndCourse(List<TbGradeDo> gradeDoList){
        // 空不必移除
        if (EmptyUtils.listIsEmpty(gradeDoList))
            return;
        List<TbGradeDo> newGradeList = new ArrayList<>();
        for (TbGradeDo g:gradeDoList
             ) {
            if (null != g.getCourseId()){
                TbCourseDo courseDo = iCourseService.getEndCourseById(g.getCourseId());
                // 空说明没有找到结课的
                if (null == courseDo)
                    newGradeList.add(g);
            }
        }
        if (EmptyUtils.listIsNotEmpty(newGradeList))
            gradeDoList = newGradeList;
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

                validateTimeScheduleDto.setGradeName(grade.getName());
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
                        || DateUtil.compareDate(targetValidateTime.getStartDate(), sourceValidateTime.getEndDate()) == -1)
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
    private void removeOneByGradeId(List<TbGradeDo> gradeDoList, Integer gradeId){
        if (EmptyUtils.listIsEmpty(gradeDoList))
            return;
        for (int i = 0; i < gradeDoList.size(); i++) {
            if (gradeDoList.get(i).getId().equals(gradeId)){
                gradeDoList.remove(i);
                return;
            }
        }
        return;
    }

    /**
     * 将校验数据中，老师id一样的放入老师安排集合中
     * @param teacherGradeList 数据库中查到的老师安排
     * @param timeScheduleList 校验数据
     * @param teacherId 老师id
     * @param gradeId 班级id
     * @return
     */
    private List<TbGradeDo> addGradeByTeacherId(List<TbGradeDo> teacherGradeList, List<CourseTimeScheduleDto> timeScheduleList, Integer teacherId, Integer gradeId) {
        if (EmptyUtils.listIsEmpty(teacherGradeList))
            teacherGradeList = new ArrayList<>();
        // 参数合法性校验
        if (EmptyUtils.listIsEmpty(timeScheduleList) || EmptyUtils.intIsEmpty(gradeId) || EmptyUtils.intIsEmpty(teacherId))
            return teacherGradeList;
        for (CourseTimeScheduleDto timeSchedule:timeScheduleList
        ) {
            TbGradeDo gradeDo = getOneById(timeSchedule.getGradeId());
            if (!EmptyUtils.objectIsEmpty(gradeDo)) {
                // 找出校验数据中，老师id一样的，同时排除本身
                if (gradeDo.getTeacherId().equals(teacherId) && !gradeDo.getId().equals(gradeId)) {
                    TbGradeDo grade = converCourseTimeScheduleDtoToTbGradeDo(timeSchedule);
                    if (!EmptyUtils.objectIsEmpty(grade))
                        teacherGradeList.add(grade);
                }

            }
        }
        return teacherGradeList;
    }

    /**
     * 将校验数据中，教室id一样的放入教室安排集合中
     * @param classroomGradeList 数据库中查到的教室安排
     * @param timeScheduleList 校验数据
     * @param classroomId 教室id
     * @param gradeId 班级id
     * @return
     */
    private List<TbGradeDo> addGradeByClassroomId(List<TbGradeDo> classroomGradeList, List<CourseTimeScheduleDto> timeScheduleList, Integer classroomId, Integer gradeId) {
        if (EmptyUtils.listIsEmpty(classroomGradeList))
            classroomGradeList = new ArrayList<>();
        // 参数合法性校验
        if (EmptyUtils.listIsEmpty(timeScheduleList) || EmptyUtils.intIsEmpty(gradeId) || EmptyUtils.intIsEmpty(classroomId))
            return classroomGradeList;
        for (CourseTimeScheduleDto timeSchedule:timeScheduleList
        ) {
            TbGradeDo gradeDo = getOneById(timeSchedule.getGradeId());
            if (!EmptyUtils.objectIsEmpty(gradeDo)) {
                // 找出校验数据中，教室id一样的，同时排除本身
                if (gradeDo.getClassroomId().equals(classroomId) && !gradeDo.getId().equals(gradeId)){
                    TbGradeDo grade =  converCourseTimeScheduleDtoToTbGradeDo(timeSchedule);
                    if (!EmptyUtils.objectIsEmpty(grade))
                        classroomGradeList.add(grade);
                }
            }
        }
        return classroomGradeList;
    }

    /**
     * 将CourseTimeScheduleDto转化为TbGradeDo
     * @param timeSchedule
     * @return
     */
    private TbGradeDo converCourseTimeScheduleDtoToTbGradeDo(CourseTimeScheduleDto timeSchedule){
        if ( EmptyUtils.objectIsEmpty(timeSchedule))
            return null;
        TbGradeDo grade = new TbGradeDo();
        grade.setId(timeSchedule.getGradeId());
        // 时间安排
        grade.setStartDate(DateUtil.stringToDateByDefaultDayFormat(timeSchedule.getDateRange().getStartDate()));
        grade.setEndDate(DateUtil.stringToDateByDefaultDayFormat(timeSchedule.getDateRange().getEndDate()));
        List<GradeWeekendsScheduleDto> gradeWeekendsScheduleDtoList = timeSchedule.convertToGradeWeekendsSchedule();
        grade.setWeekendsSchedule(JSONUtil.toJSONString(gradeWeekendsScheduleDtoList));

        return grade;
    }
}
