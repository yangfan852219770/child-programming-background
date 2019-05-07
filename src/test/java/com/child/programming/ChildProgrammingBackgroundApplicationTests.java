package com.child.programming;

import com.child.programming.base.mapper.TbClassroomDoMapper;
import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.base.service.ICourseService;
import com.child.programming.base.service.IGradeService;
import com.child.programming.education.manage.dto.CourseScheduleDto;
import com.child.programming.education.manage.dto.WeekendsScheduleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@RunWith(SpringRunner.class)
@MapperScan("com.child.programming.base.mapper")
@SpringBootTest
public class ChildProgrammingBackgroundApplicationTests {
    @Autowired
    private TbClassroomDoMapper classroomDoMapper;
    @Autowired
    private IGradeService iGradeService;

    @Autowired
    private ICourseService iCourseService;


    @Test
    public void contextLoads() {
        TbClassroomDo classroomDto = new TbClassroomDo();
        classroomDto.setCode(123);
        classroomDto.setStatus(Byte.valueOf("1"));
        classroomDoMapper.insert(classroomDto);
        System.out.println("test");
    }

    @Test
    public void queryList(){
        Map map = new HashMap();
        iGradeService.getList(map);
    }

    @Test
    public void generateScheduleTest(){
        System.out.println("Test");
        Boolean result = iCourseService.generateCourseSchedule(1, 1);
        System.out.println(result);
    }

    @Test
    public void convertToCourseScheduleTest(){
        System.out.println("Test");
        TbGradeDo gradeDo = iGradeService.getOneById(2);
        List<CourseScheduleDto> courseScheduleDtoList = iGradeService.convertToCourseSchedule(gradeDo);
        System.out.println(courseScheduleDtoList);
    }
}