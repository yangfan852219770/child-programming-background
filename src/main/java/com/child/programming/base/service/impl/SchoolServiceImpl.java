package com.child.programming.base.service.impl;

import com.child.programming.base.dto.SchoolInfoDto;
import com.child.programming.base.mapper.TbSchoolDoMapper;
import com.child.programming.base.model.TbSchoolDo;
import com.child.programming.base.model.TbSchoolDoExample;
import com.child.programming.base.service.ISchoolService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.education.manage.dto.SelectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class SchoolServiceImpl implements ISchoolService {
    @Autowired
    private TbSchoolDoMapper tbSchoolDoMapper;

    @Override
    public List<SchoolInfoDto> getList(String name) {
        TbSchoolDoExample schoolDoExample = new TbSchoolDoExample();
        TbSchoolDoExample.Criteria criteria = schoolDoExample.createCriteria();

        schoolDoExample.setOrderByClause("create_time desc");
        if (!EmptyUtils.stringIsEmpty(name))
            criteria.andStatusEqualTo(Byte.valueOf("1")).andNameLike("%" + name + "%");
        else
            criteria.andStatusEqualTo(Byte.valueOf("1"));

        List<TbSchoolDo> schoolDoList = tbSchoolDoMapper.selectByExample(schoolDoExample);
        if (!EmptyUtils.listIsEmpty(schoolDoList))
            return ListUtil.convertElement(schoolDoList, SchoolInfoDto.class);
        else
            return null;
    }

    @Override
    public Boolean save(TbSchoolDo schoolDo, Integer userId) {
        if (null == schoolDo)
            return false;

        //插入
        if (null == schoolDo.getId()){
            schoolDo.setCreateId(userId);
            schoolDo.setCreateTime(new Date());
            schoolDo.setStatus(Byte.valueOf("1"));
            return tbSchoolDoMapper.insert(schoolDo) > 0;
        } else{
            //更新
            schoolDo.setLastUpdateId(userId);
            schoolDo.setLastUpdateTime(new Date());
            return tbSchoolDoMapper.updateByPrimaryKeySelective(schoolDo) > 0;
        }
    }


    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
             ) {
            TbSchoolDo schoolDo = tbSchoolDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != schoolDo){
                schoolDo.setStatus(Byte.valueOf("0"));
                schoolDo.setLastUpdateId(userId);
                schoolDo.setLastUpdateTime(new Date());
                result += tbSchoolDoMapper.updateByPrimaryKeySelective(schoolDo);
            }
        }

        return result == idArray.length;
    }

    @Override
    public List<SelectDto> getSchoolInfoSelectList() {
        TbSchoolDoExample example = new TbSchoolDoExample();
        TbSchoolDoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1"));

        List<TbSchoolDo> schoolDtoList = tbSchoolDoMapper.selectByExample(example);
        if (!EmptyUtils.listIsEmpty(schoolDtoList)){
            List<SelectDto> selectDtoList = new ArrayList<>();
            for (TbSchoolDo tbSchoolDo:schoolDtoList
            ) {
                SelectDto selectDto = new SelectDto();

                selectDto.setValue(tbSchoolDo.getId());
                selectDto.setLabel(tbSchoolDo.getName());

                selectDtoList.add(selectDto);
            }
            return selectDtoList;
        }
        return null;
    }
}
