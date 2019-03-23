package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbSchoolDtoMapper;
import com.child.programming.base.model.TbSchoolDto;
import com.child.programming.base.model.TbSchoolDtoExample;
import com.child.programming.base.service.ISchoolService;
import com.child.programming.base.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class SchoolServiceImpl implements ISchoolService {
    @Autowired
    private TbSchoolDtoMapper tbSchoolDtoMapper;

    @Override
    public List<TbSchoolDto> getList(String name) {
        TbSchoolDtoExample schoolDtoExample = new TbSchoolDtoExample();
        TbSchoolDtoExample.Criteria criteria = schoolDtoExample.createCriteria();

        schoolDtoExample.setOrderByClause("create_time desc");
        if (!StringUtils.isEmpty(name))
            criteria.andStatusEqualTo(Byte.valueOf("1")).andNameLike("%" + name + "%");
        else
            criteria.andStatusEqualTo(Byte.valueOf("1"));

        return tbSchoolDtoMapper.selectByExample(schoolDtoExample);
    }

    @Override
    public Boolean save(TbSchoolDto schoolDto, Integer userId) {
        if (null == schoolDto)
            return false;

        //插入
        if (null == schoolDto.getId()){
            schoolDto.setCreateId(userId);
            schoolDto.setCreateTime(new Date());
            schoolDto.setStatus(Byte.valueOf("1"));
            return tbSchoolDtoMapper.insert(schoolDto) > 0 ? true : false;
        } else{
            //更新
            schoolDto.setLastUpdateId(userId);
            schoolDto.setLastUpdateTime(new Date());
            return tbSchoolDtoMapper.updateByPrimaryKeySelective(schoolDto) > 0 ? true : false;
        }
    }


    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtil.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
             ) {
            TbSchoolDto schoolDto = tbSchoolDtoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != schoolDto){
                schoolDto.setStatus(Byte.valueOf("0"));
                schoolDto.setLastUpdateId(userId);
                schoolDto.setLastUpdateTime(new Date());
                result += tbSchoolDtoMapper.updateByPrimaryKeySelective(schoolDto);
            }
        }

        return result == idArray.length;
    }
}
