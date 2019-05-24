package com.child.programming.base.service;

import com.child.programming.base.dto.SignUpFormalCourseInfoDto;
import com.child.programming.base.model.TbStudentSignUpDo;

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

    /**
     * 查询缴费、不是中途报名
     * @param gradeId
     * @return
     */
    List<TbStudentSignUpDo> getListByGradeId(Integer gradeId);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    TbStudentSignUpDo getOneById(Integer id);

    /**
     * 更新
     * @param studentSignUpDo
     * @param userId
     * @return
     */
    Boolean update(TbStudentSignUpDo studentSignUpDo, Integer userId);

    /**
     * 未有人缴费，则不必生成课表
     * @param gradeId
     * @return
     */
    List<TbStudentSignUpDo> getListPayByGradeId(Integer gradeId);
}
