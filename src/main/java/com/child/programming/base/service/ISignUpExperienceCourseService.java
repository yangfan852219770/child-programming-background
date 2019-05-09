package com.child.programming.base.service;

import com.child.programming.base.dto.SignUpExperienceCourseInfoDto;
import com.child.programming.base.model.TbSignUpExperienceCourseDo;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface ISignUpExperienceCourseService {

    /**
     * 体验课报名信息列表
     * @param map 学生姓名
     * @return
     */
    List<SignUpExperienceCourseInfoDto>  getList(Map map);

    /**
     * 删除
     * @param idArray
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);

    /**
     * 更新
     * @param signUpExperienceCourseDo
     * @param userId
     * @return
     */
    Boolean update(TbSignUpExperienceCourseDo signUpExperienceCourseDo, Integer userId);
}
