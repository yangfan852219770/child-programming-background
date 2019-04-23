package com.child.programming.base.service.impl;

import com.child.programming.base.dto.StudentInfoDto;
import com.child.programming.base.mapper.TbCollectCourseDoMapper;
import com.child.programming.base.mapper.TbSignUpExperienceCourseDoMapper;
import com.child.programming.base.mapper.TbStudentDoMapper;
import com.child.programming.base.mapper.TbStudentSignUpDoMapper;
import com.child.programming.base.model.*;
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
    @Autowired
    private TbCollectCourseDoMapper tbCollectCourseDoMapper;

    /**
     * @Description:    据openId查询学生信息
     */
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

    /**
     * @Description:    根据openId修改学生信息
     */
    @Override
    public int updateStudentByOpenId(TbStudentDo studentDto) {
        TbStudentDoExample example = new TbStudentDoExample();
        TbStudentDoExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(studentDto.getOpenid());
        int result = tbStudentDoMapper.updateByExampleSelective(studentDto,example);
        return result;
    }

    /**
     * @Description:    添加学生信息
     */
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

    /**
     * @Description:    报名课程
     */
    @Override
    public int signUpCourse(TbStudentSignUpDo studentSignUpDo) {
        return tbStudentSignUpDoMapper.insert(studentSignUpDo);
    }

    /**
     * @Description:    报名体验课
     */
    @Override
    public int signUpExperienceCourse(TbSignUpExperienceCourseDo signUpExperienceCourseDo) {
        return tbSignUpExperienceCourseDoMapper.insert(signUpExperienceCourseDo);
    }

    /**
     * @Description:    是否收藏此课程
     */
    @Override
    public Boolean isCollectCourse(int courseId, int studentId) {
        TbCollectCourseDoExample example = new TbCollectCourseDoExample();
        TbCollectCourseDoExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        criteria.andStudentIdEqualTo(studentId);
        criteria.andStatusEqualTo((byte) 1);
        List<TbCollectCourseDo> collectCourseDos = tbCollectCourseDoMapper.selectByExample(example);
        if (collectCourseDos!=null && collectCourseDos.size()>0){
            return true;
        }
        return false;
    }

    /**
     * @Description:    收藏此课程
     */
    @Override
    public int saveCollectCourse(int courseId, int studentId) {
        TbCollectCourseDo collectCourseDo = new TbCollectCourseDo();
        collectCourseDo.setCourseId(courseId);
        collectCourseDo.setStudentId(studentId);
        collectCourseDo.setCreateTime(new Date());
        collectCourseDo.setStatus((byte) 1);
        return tbCollectCourseDoMapper.insertSelective(collectCourseDo);
    }

    /**
     * @Description:    取消收藏此课程
     */
    @Override
    public int deleteCollectCourse(int courseId, int studentId) {
        TbCollectCourseDoExample example = new TbCollectCourseDoExample();
        TbCollectCourseDoExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        criteria.andStudentIdEqualTo(studentId);
        criteria.andStatusEqualTo((byte) 1);
        return tbCollectCourseDoMapper.deleteByExample(example);
    }

    /**
     * @Description:    查询此课程是否已经报名
     */
    @Override
    public List<TbStudentSignUpDo> getStudentSignUpByClassIdAndStudentId(int gradeId, int studentId) {
        TbStudentSignUpDoExample example = new TbStudentSignUpDoExample();
        TbStudentSignUpDoExample.Criteria criteria = example.createCriteria();
        criteria.andStudentIdEqualTo(studentId);
        criteria.andGradeIdEqualTo(gradeId);
        criteria.andStatusEqualTo((byte) 1);
        List<TbStudentSignUpDo> studentSignUpDos = tbStudentSignUpDoMapper.selectByExample(example);
        return studentSignUpDos;
    }

    /**
     * @Description:    查询此体验课是否已经报名
     */
    @Override
    public List<TbSignUpExperienceCourseDo> getsignUpExperienceCourseByExperienceCourseIdAndStudentId(int experienceCourseId, int studentId) {
        TbSignUpExperienceCourseDoExample experienceCourseDoExample = new TbSignUpExperienceCourseDoExample();
        TbSignUpExperienceCourseDoExample.Criteria criteria = experienceCourseDoExample.createCriteria();
        criteria.andExperienceCourseIdEqualTo(experienceCourseId);
        criteria.andStudentIdEqualTo(studentId);
        criteria.andStatusEqualTo((byte) 1);
        List<TbSignUpExperienceCourseDo> experienceCourseDos = tbSignUpExperienceCourseDoMapper.selectByExample(experienceCourseDoExample);
        return experienceCourseDos;
    }
}
