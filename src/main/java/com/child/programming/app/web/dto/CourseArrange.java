package com.child.programming.app.web.dto;

import java.util.Date;

public class CourseArrange {

    private int id;
    private String schoolAddress;
    private String schoolName;
    private int classroomCode;
    private String gradeName;
    private String teacherName;
    private int capacity;
    private int currentPeriod;
    private Date startDate;
    private Date endDate;
    private String weekendsSchedule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(int classroomCode) {
        this.classroomCode = classroomCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWeekendsSchedule() {
        return weekendsSchedule;
    }

    public void setWeekendsSchedule(String weekendsSchedule) {
        this.weekendsSchedule = weekendsSchedule;
    }
}
