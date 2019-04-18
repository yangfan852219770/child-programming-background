package com.child.programming.base.service.impl;

import com.child.programming.base.dto.ExperienceCourseInfoDto;
import com.child.programming.base.mapper.TbExperienceCourseDoMapper;
import com.child.programming.base.mapper.TbShareCircleDoMapper;
import com.child.programming.base.model.TbExperienceCourseDo;
import com.child.programming.base.model.TbExperienceCourseDoExample;
import com.child.programming.base.model.TbShareCircleDo;
import com.child.programming.base.model.TbShareCircleDoExample;
import com.child.programming.base.service.IExperienceCourseService;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExperienceCourseServiceImpl implements IExperienceCourseService {

    @Autowired
    private TbExperienceCourseDoMapper experienceCourseDoMapper;
    @Autowired
    private TbShareCircleDoMapper shareCircleDoMapper;

    /**
     * @Description:    所有准备开课的体验课列表
     */
    @Override
    public List<TbExperienceCourseDo> getAllExperienceCourse() {
        TbExperienceCourseDoExample example = new TbExperienceCourseDoExample();
        example.setOrderByClause("sign_up_end_date asc");
        TbExperienceCourseDoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andSignUpEndDateGreaterThan(new Date()); // >
        return experienceCourseDoMapper.selectByExample(example);
    }

    /**
     * @Description:    分享课程是否已经分享过
     */
    @Override
    public List<TbShareCircleDo> getShareCircleByStudentIdAndCourseId(int studentId, int experienceCourseId) {
        TbShareCircleDoExample example = new TbShareCircleDoExample();
        TbShareCircleDoExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(experienceCourseId);
        criteria.andStudentIdEqualTo(studentId);
        criteria.andStatusEqualTo((byte) 1);
        List<TbShareCircleDo> list = shareCircleDoMapper.selectByExample(example);
        return list;
    }

    /**
     * @Description:    保存分享课程
     */
    @Override
    public int insertTbShareCircle(TbShareCircleDo shareCircleDo) {
        return shareCircleDoMapper.insertSelective(shareCircleDo);
    }

    @Override
    public List<TbShareCircleDo> getShareCircleByCourseIdAndShareCode(int experienceCourseId, String shareCodeText) {
        TbShareCircleDoExample example = new TbShareCircleDoExample();
        TbShareCircleDoExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(experienceCourseId);
        criteria.andEclusiveCodeEqualTo(shareCodeText);
        criteria.andStatusEqualTo((byte) 1);
        List<TbShareCircleDo> list = shareCircleDoMapper.selectByExample(example);
        return list;
    }

    @Override
    public int updateShareCodeCount(TbShareCircleDo tbShareCircleDo) {
        return shareCircleDoMapper.updateByPrimaryKey(tbShareCircleDo);
    }

    @Override
    public List<ExperienceCourseInfoDto> getList(String title) {
        TbExperienceCourseDoExample example = new TbExperienceCourseDoExample();
        example.setOrderByClause("create_time desc");
        TbExperienceCourseDoExample.Criteria criteria = example.createCriteria();
        if (EmptyUtils.stringIsNotEmpty(title))
            criteria.andTitleLike("%" + title + "%").andStatusNotEqualTo(0);
        criteria.andStatusNotEqualTo(0);
        List<TbExperienceCourseDo> experienceCourseDoList = experienceCourseDoMapper.selectByExample(example);
        if (EmptyUtils.listIsNotEmpty(experienceCourseDoList))
            return ListUtil.convertElement(experienceCourseDoList, ExperienceCourseInfoDto.class);
        return null;
    }

    @Override
    public Boolean save(Integer userId, TbExperienceCourseDo experienceCourseDo) {
        if (null == experienceCourseDo)
            return false;

        //插入
        if (null == experienceCourseDo.getId()){
            experienceCourseDo.setStatus(1);
            experienceCourseDo.setCreateId(userId);
            experienceCourseDo.setCreateTime(new Date());
            return experienceCourseDoMapper.insert(experienceCourseDo) > 0;
        }else {
            // 更新
            experienceCourseDo.setLastUpdateId(userId);
            experienceCourseDo.setLastUpdateTime(new Date());
            return experienceCourseDoMapper.updateByPrimaryKeySelective(experienceCourseDo) > 0;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
        ) {
            TbExperienceCourseDo experienceCourseDo = experienceCourseDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != experienceCourseDo){
                experienceCourseDo.setStatus(0);
                experienceCourseDo.setLastUpdateId(userId);
                experienceCourseDo.setLastUpdateTime(new Date());
                result += experienceCourseDoMapper.updateByPrimaryKeySelective(experienceCourseDo);
            }
        }

        return result == idArray.length;
    }
}
