package com.child.programming.base.service;

import com.child.programming.base.dto.SignUpFormalCourseInfoDto;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface ISignUpFormalCourseService {

    /**
     * 正式课报名信息列表
     * @param map 学生姓名
     * @return
     */
    List<SignUpFormalCourseInfoDto>  getList(Map map);

    /**
     * 删除
     * @param idArray
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);
}
