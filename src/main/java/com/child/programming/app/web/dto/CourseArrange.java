package com.child.programming.app.web.dto;

import java.util.Date;

public class CourseArrange {

    private int id;
    private String gradeAddress;
    private String gradeName;
    private String teacherName;
    private int capacity;
    private int currentPeriod;
    private String druingDate;
    private String weekendsSchedule;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradeAddress() {
        return gradeAddress;
    }

    public void setGradeAddress(String gradeAddress) {
        this.gradeAddress = gradeAddress;
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

    public String getDruingDate() {
        return druingDate;
    }

    public void setDruingDate(String druingDate) {
        this.druingDate = druingDate;
    }

    public String getWeekendsSchedule() {
        return weekendsSchedule;
    }

    public void setWeekendsSchedule(String weekendsSchedule) {
        this.weekendsSchedule = weekendsSchedule;
    }
}
