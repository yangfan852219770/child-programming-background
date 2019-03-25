package com.child.programming.education.manage.service;

import com.child.programming.education.manage.dto.SchoolInfoSelectDto;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface IAntSchoolService {

    List<SchoolInfoSelectDto> getSchoolInfoSelectList();
}
