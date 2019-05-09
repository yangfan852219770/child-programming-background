package com.child.programming.base.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zdp
 * @description: TODO
 */
@Data
public class StudentWorkInfoDto {

    private Integer id;  // Students work id

    private Integer studentId; // StudentId

    private Integer teacherId; // TeacherId

    private String studentName; // StudentName

    private String teacherName; // TeacherName

    private String workUrl; // WorksUrl

    private String workName; // WorkName

    private String description; //works Description

    private String pageView;

    private Integer collectionNumber;

    private String likeCount;

    private String workCreateTime;

    private Byte status;

}
