package com.child.programming.base.service;

import com.child.programming.base.model.TbSchoolDto;

/**
 * @Description：
 * @Author：yangfan
 **/
public interface ISchoolService {

    /**
     * 保存
     * @param schoolDto
     * @return
     */
    Boolean addOne(TbSchoolDto schoolDto);
}
