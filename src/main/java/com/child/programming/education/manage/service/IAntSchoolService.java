package com.child.programming.education.manage.service;

import com.child.programming.education.manage.pojo.AntSchoolInfoSelectPojo;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface IAntSchoolService {

    List<AntSchoolInfoSelectPojo> getSchoolInfoSelectList();
}
