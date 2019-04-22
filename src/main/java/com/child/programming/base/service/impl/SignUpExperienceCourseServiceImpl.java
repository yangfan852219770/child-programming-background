package com.child.programming.base.service.impl;

import com.child.programming.base.dto.SignUpExperienceCourseInfoDto;
import com.child.programming.base.mapper.SignUpExperienceCourseCustomMapper;
import com.child.programming.base.mapper.TbSignUpExperienceCourseDoMapper;
import com.child.programming.base.model.TbSignUpExperienceCourseDo;
import com.child.programming.base.service.ISignUpExperienceCourseService;
import com.child.programming.base.util.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<SignUpExperienceCourseInfoDto> getList(Map map) {
        return signUpExperienceCourseCustomMapper.getList(map);
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
        ) {
            TbSignUpExperienceCourseDo signUpExperienceCourseDo = signUpExperienceCourseDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != signUpExperienceCourseDo){
                signUpExperienceCourseDo.setStatus(Byte.valueOf("0"));
                signUpExperienceCourseDo.setLastUpdateId(userId);
                signUpExperienceCourseDo.setLastUpdateTime(new Date());
                result += signUpExperienceCourseDoMapper.updateByPrimaryKeySelective(signUpExperienceCourseDo);
            }
        }

        return result == idArray.length;
    }
}
