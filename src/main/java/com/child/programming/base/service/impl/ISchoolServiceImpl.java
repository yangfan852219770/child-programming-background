package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbSchoolDtoMapper;
import com.child.programming.base.model.TbSchoolDto;
import com.child.programming.base.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class ISchoolServiceImpl implements ISchoolService {
    @Autowired
    private TbSchoolDtoMapper tbSchoolDtoMapper;

    @Override
    public Boolean addOne(TbSchoolDto schoolDto) {
        return tbSchoolDtoMapper.insert(schoolDto) > 0 ? true : false;
    }
}
