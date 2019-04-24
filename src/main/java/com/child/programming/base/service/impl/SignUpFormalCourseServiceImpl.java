package com.child.programming.base.service.impl;

import com.child.programming.base.dto.SignUpFormalCourseInfoDto;
import com.child.programming.base.mapper.SignUpFormalCourseCustomMapper;
import com.child.programming.base.mapper.TbStudentSignUpDoMapper;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.service.ISignUpFormalCourseService;
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
public class SignUpFormalCourseServiceImpl implements ISignUpFormalCourseService {

    @Autowired
    private TbStudentSignUpDoMapper studentSignUpDoMapper;

    @Autowired
    private SignUpFormalCourseCustomMapper signUpFormalCourseCustomMapper;

    @Override
    public List<SignUpFormalCourseInfoDto> getList(Map map) {
        return signUpFormalCourseCustomMapper.getList(map);
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
        ) {
            TbStudentSignUpDo studentSignUpDo = studentSignUpDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != studentSignUpDo){
                studentSignUpDo.setStatus(Byte.valueOf("0"));
                studentSignUpDo.setLastUpdateId(userId);
                studentSignUpDo.setLastUpdateTime(new Date());
                result += studentSignUpDoMapper.updateByPrimaryKeySelective(studentSignUpDo);
            }
        }

        return result == idArray.length;
    }
}
