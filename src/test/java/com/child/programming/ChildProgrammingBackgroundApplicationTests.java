package com.child.programming;

import com.child.programming.base.mapper.TbClassroomDoMapper;
import com.child.programming.base.model.TbClassroomDo;
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
    private TbClassroomDoMapper classroomDoMapper;

    @Test
    public void contextLoads() {
        TbClassroomDo classroomDto = new TbClassroomDo();
        classroomDto.setCode(123);
        classroomDto.setStatus(Byte.valueOf("1"));
        classroomDoMapper.insert(classroomDto);
        System.out.println("test");
    }


}
