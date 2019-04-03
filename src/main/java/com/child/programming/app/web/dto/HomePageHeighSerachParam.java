package com.child.programming.app.web.dto;

import org.springframework.util.StringUtils;

import java.util.Date;

public class HomePageHeighSerachParam {
    private String page;
    private String limit;
    private String courseName;
    private String selectSchoolId;
    private String teacherId;
    private String lowPrice;
    private String heighPrice;
    private String lowDate;
    private String heighDate;
    private String heighSearchFlg;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSelectSchoolId() {
        return selectSchoolId;
    }

    public void setSelectSchoolId(String selectSchoolId) {
        this.selectSchoolId = selectSchoolId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHeighPrice() {
        return heighPrice;
    }

    public void setHeighPrice(String heighPrice) {
        this.heighPrice = heighPrice;
    }

    public String getLowDate() {
        return lowDate;
    }

    public void setLowDate(String lowDate) {
        this.lowDate = lowDate;
    }

    public String getHeighDate() {
        return heighDate;
    }

    public void setHeighDate(String heighDate) {
        this.heighDate = heighDate;
    }

    public String getHeighSearchFlg() {
        return heighSearchFlg;
    }

    public void setHeighSearchFlg(String heighSearchFlg) {
        this.heighSearchFlg = heighSearchFlg;
    }
}
