package com.child.programming.education.manage.service.impl;

import com.child.programming.base.mapper.TbSchoolDtoMapper;
import com.child.programming.base.model.TbSchoolDto;
import com.child.programming.base.model.TbSchoolDtoExample;
import com.child.programming.base.util.EmptyUtil;
import com.child.programming.education.manage.pojo.AntSchoolInfoSelectPojo;
import com.child.programming.education.manage.service.IAntSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/

@Service
public class AntSchoolServiceImpl implements IAntSchoolService {
    @Autowired
    private TbSchoolDtoMapper schoolDtoMapper;

    @Override
    public List<AntSchoolInfoSelectPojo> getSchoolInfoSelectList() {
        TbSchoolDtoExample example = new TbSchoolDtoExample();
        TbSchoolDtoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1"));

        List<TbSchoolDto> schoolDtoList = schoolDtoMapper.selectByExample(example);
        if (!EmptyUtil.listIsEmpty(schoolDtoList)){
            List<AntSchoolInfoSelectPojo> antSchoolInfoSelectPojoList = new ArrayList<>();
            for (TbSchoolDto tbSchoolDto:schoolDtoList
                 ) {
                AntSchoolInfoSelectPojo antSchoolInfoSelectPojo = new AntSchoolInfoSelectPojo();

                antSchoolInfoSelectPojo.setKey(tbSchoolDto.getId());
                antSchoolInfoSelectPojo.setValue(tbSchoolDto.getName());

                antSchoolInfoSelectPojoList.add(antSchoolInfoSelectPojo);
            }
            return antSchoolInfoSelectPojoList;
        }
        return null;
    }
}
