package com.child.programming.education.manage.service.impl;

import com.child.programming.base.mapper.TbSchoolDoMapper;
import com.child.programming.base.model.TbSchoolDo;
import com.child.programming.base.model.TbSchoolDoExample;
import com.child.programming.base.util.EmptyUtil;
import com.child.programming.education.manage.dto.SchoolInfoSelectDto;
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
    private TbSchoolDoMapper tbSchoolDoMapper;

    @Override
    public List<SchoolInfoSelectDto> getSchoolInfoSelectList() {
        TbSchoolDoExample example = new TbSchoolDoExample();
        TbSchoolDoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1"));

        List<TbSchoolDo> schoolDtoList = tbSchoolDoMapper.selectByExample(example);
        if (!EmptyUtil.listIsEmpty(schoolDtoList)){
            List<SchoolInfoSelectDto> schoolInfoSelectDtoList = new ArrayList<>();
            for (TbSchoolDo tbSchoolDo:schoolDtoList
                 ) {
                SchoolInfoSelectDto schoolInfoSelectDto = new SchoolInfoSelectDto();

                schoolInfoSelectDto.setKey(tbSchoolDo.getId());
                schoolInfoSelectDto.setValue(tbSchoolDo.getName());

                schoolInfoSelectDtoList.add(schoolInfoSelectDto);
            }
            return schoolInfoSelectDtoList;
        }
        return null;
    }
}
