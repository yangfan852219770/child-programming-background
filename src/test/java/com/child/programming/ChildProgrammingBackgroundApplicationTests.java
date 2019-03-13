package com.child.programming;

import com.child.programming.base.mapper.TbClassroomDtoMapper;
import com.child.programming.base.model.TbClassroomDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

@RunWith(SpringRunner.class)
@MapperScan("com.child.programming.base.mapper")
@SpringBootTest
public class ChildProgrammingBackgroundApplicationTests {
    @Autowired
    private TbClassroomDtoMapper classroomDtoMapper;

    @Test
    public void contextLoads() {
        TbClassroomDto classroomDto = new TbClassroomDto();
        classroomDto.setCode(123);
        classroomDto.setStatus(Byte.valueOf("1"));
        classroomDtoMapper.insert(classroomDto);
        System.out.println("test");
    }


}
