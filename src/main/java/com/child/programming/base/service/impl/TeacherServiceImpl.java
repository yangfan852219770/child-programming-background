package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbTeacherDoMapper;
import com.child.programming.base.model.TbTeacherDo;
import com.child.programming.base.model.TbTeacherDoExample;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.EmptyUtil;
import com.child.programming.base.util.ListUtil;
import com.child.programming.education.manage.dto.TeacherInfoDto;
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
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TbTeacherDoMapper tbTeacherDoMapper;

    @Override
    public List<TeacherInfoDto> getList(String name) {
        TbTeacherDoExample example = new TbTeacherDoExample();
        TbTeacherDoExample.Criteria criteria = example.createCriteria();

        example.setOrderByClause("create_time desc");
        if (!StringUtils.isEmpty(name))
            criteria.andStatusEqualTo(Byte.valueOf("1")).andNameLike("%" + name + "%");
        else
            criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbTeacherDo> teacherDoList = tbTeacherDoMapper.selectByExample(example);
        if (!EmptyUtil.listIsEmpty(teacherDoList))
            return ListUtil.convertElement(teacherDoList, TeacherInfoDto.class);
        else
            return null;
    }

    @Override
    public Boolean save(TbTeacherDo teacherDo, Integer userId) {
        if (null == teacherDo)
            return false;

        //插入
        if (null == teacherDo.getId()){
            teacherDo.setStatus(Byte.valueOf("1"));
            teacherDo.setCreateId(userId);
            teacherDo.setCreateTime(new Date());
            return tbTeacherDoMapper.insert(teacherDo) > 0 ? true : false;
        }else {
            teacherDo.setLastUpdateId(userId);
            teacherDo.setLastUpdateTime(new Date());
            return tbTeacherDoMapper.updateByPrimaryKeySelective(teacherDo) > 0 ? true : false;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtil.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
        ) {
            TbTeacherDo teacherDo = tbTeacherDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != teacherDo){
                teacherDo.setStatus(Byte.valueOf("0"));
                teacherDo.setLastUpdateId(userId);
                teacherDo.setLastUpdateTime(new Date());
                result += tbTeacherDoMapper.updateByPrimaryKeySelective(teacherDo);
            }
        }

        return result == idArray.length;
    }
}
