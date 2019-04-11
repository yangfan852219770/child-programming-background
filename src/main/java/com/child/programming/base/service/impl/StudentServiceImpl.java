package com.child.programming.base.service.impl;

import com.child.programming.base.dto.StudentInfoDto;
import com.child.programming.base.mapper.TbSignUpExperienceCourseDoMapper;
import com.child.programming.base.mapper.TbStudentDoMapper;
import com.child.programming.base.mapper.TbStudentSignUpDoMapper;
import com.child.programming.base.model.TbSignUpExperienceCourseDo;
import com.child.programming.base.model.TbStudentDo;
import com.child.programming.base.model.TbStudentDoExample;
import com.child.programming.base.model.TbStudentSignUpDo;
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
    @Autowired
    private TbStudentSignUpDoMapper tbStudentSignUpDoMapper;
    @Autowired
    private TbSignUpExperienceCourseDoMapper tbSignUpExperienceCourseDoMapper;

    @Override
    public TbStudentDo getStudentByOpenId(String openid) {
        TbStudentDoExample example = new TbStudentDoExample();
        TbStudentDoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openid);
        List<TbStudentDo> list = tbStudentDoMapper.selectByExample(example);
        if (list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int updateStudentByOpenId(TbStudentDo studentDto) {
        TbStudentDoExample example = new TbStudentDoExample();
        TbStudentDoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(studentDto.getOpenid());
        int result = tbStudentDoMapper.updateByExampleSelective(studentDto,example);
        return result;
    }

    @Override
    public int addStudent(TbStudentDo studentDto) {
        int result = tbStudentDoMapper.insert(studentDto);
        return result;
    }

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
           return tbStudentDoMapper.insert(tbStudentDo)>0;
       } else{
           //更新
           tbStudentDo.setLastUpdateId(currentUserId);
           tbStudentDo.setLastUpdateTime(new Date());
           return tbStudentDoMapper.updateByPrimaryKeySelective(tbStudentDo)>0;
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

    @Override
    public int signUpCourse(TbStudentSignUpDo studentSignUpDo) {
        return tbStudentSignUpDoMapper.insert(studentSignUpDo);
    }

    @Override
    public int signUpExperienceCourse(TbSignUpExperienceCourseDo signUpExperienceCourseDo) {
        return tbSignUpExperienceCourseDoMapper.insert(signUpExperienceCourseDo);
    }
}
