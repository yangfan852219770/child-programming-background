package com.child.programming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.child.programming.base.mapper")
public class ChildProgrammingBackgroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChildProgrammingBackgroundApplication.class, args);
    }

}
