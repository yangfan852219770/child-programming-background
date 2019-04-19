package com.child.programming.base.service.impl;

import com.child.programming.base.dto.SignUpExperienceCourseInfoDto;
import com.child.programming.base.mapper.SignUpExperienceCourseCustomMapper;
import com.child.programming.base.mapper.TbSignUpExperienceCourseDoMapper;
import com.child.programming.base.service.ISignUpExperienceCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class SignUpExperienceCourseServiceImpl implements ISignUpExperienceCourseService {
    @Autowired
    private TbSignUpExperienceCourseDoMapper signUpExperienceCourseDoMapper;

    @Autowired
    private SignUpExperienceCourseCustomMapper signUpExperienceCourseCustomMapper;

    @Override
    public List<SignUpExperienceCourseInfoDto> getList() {
        return null;
    }
}
