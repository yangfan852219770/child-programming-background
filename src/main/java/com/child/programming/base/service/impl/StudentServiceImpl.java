package com.child.programming.base.service.impl;

import com.child.programming.base.dto.StudentInfoDto;
import com.child.programming.base.mapper.TbStudentDoMapper;
import com.child.programming.base.model.TbStudentDo;
import com.child.programming.base.model.TbStudentDoExample;
import com.child.programming.base.service.IStudentService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@Service
public class StudentServiceImpl  implements IStudentService{

    @Autowired
    private TbStudentDoMapper tbStudentDoMapper;

    @Override
    public List<StudentInfoDto> getList(String name) {

        TbStudentDoExample example= new TbStudentDoExample();
        TbStudentDoExample.Criteria criteria =example.createCriteria();
        example.setOrderByClause("create_time desc");
        if(!EmptyUtils.stringIsEmpty(name))
            criteria.andStatusEqualTo(Byte.valueOf("1")).andNameLike("%" + name + "%");
        else
            criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbStudentDo> tbStudentDos=tbStudentDoMapper.selectByExample(example);
        if(!EmptyUtils.listIsEmpty(tbStudentDos))
            return  ListUtil.convertElement(tbStudentDos,StudentInfoDto.class);
        else
            return null;
    }

    @Override
    public Boolean save(TbStudentDo tbStudentDo, Integer currentUserId) {
       if(EmptyUtils.objectIsEmpty(tbStudentDo))
           return false;
       //插入
       if(EmptyUtils.intIsEmpty(tbStudentDo.getId())){
           tbStudentDo.setCreateId(currentUserId);
           tbStudentDo.setCreateTime(new Date());
           tbStudentDo.setStatus(Byte.valueOf("1"));
           return tbStudentDoMapper.insert(tbStudentDo)>0?true:false;
       } else{
           //更新
           tbStudentDo.setLastUpdateId(currentUserId);
           tbStudentDo.setLastUpdateTime(new Date());
           return tbStudentDoMapper.updateByPrimaryKeySelective(tbStudentDo)>0?true:false;
       }
    }

    @Override
    public Boolean delete(String[] idArray, Integer currentUserId) {
        if(EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result=0;
        for (String idStr:idArray
             ) {
            TbStudentDo tbStudentDo=tbStudentDoMapper.selectByPrimaryKey(Integer.parseInt(idStr));
            if(!EmptyUtils.objectIsEmpty(tbStudentDo)){
                tbStudentDo.setStatus(Byte.valueOf("0"));
                tbStudentDo.setLastUpdateId(currentUserId);
                tbStudentDo.setLastUpdateTime(new Date());
                result+=tbStudentDoMapper.updateByPrimaryKeySelective(tbStudentDo);
            }
        }

        return result==idArray.length;
    }
}
