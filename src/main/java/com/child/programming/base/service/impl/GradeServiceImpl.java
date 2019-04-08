package com.child.programming.base.service.impl;

import com.child.programming.base.dto.GradeInfoDto;
import com.child.programming.base.mapper.GradeCustomMapper;
import com.child.programming.base.mapper.TbGradeDoMapper;
import com.child.programming.base.model.TbGradeDo;
import com.child.programming.base.model.TbGradeDoExample;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.service.IGradeService;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.education.manage.dto.InitGradeInfoDto;
import com.child.programming.education.manage.dto.SelectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description：
 * @Author：yangfan
 **/
@Service
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private TbGradeDoMapper tbGradeDoMapper;
    @Autowired
    private GradeCustomMapper gradeCustomMapper;

    @Autowired
    private ITeacherService iTeacherService;
    @Autowired
    private IClassroomService iClassroomService;

    @Override
    public List<GradeInfoDto> getList(Map map) {
        List<GradeInfoDto> gradeInfoDtoList = gradeCustomMapper.getList(map);
        if (EmptyUtils.listIsNotEmpty(gradeInfoDtoList)){
            for (GradeInfoDto gradeInfo:gradeInfoDtoList
            ) {
                Integer[] ids = {gradeInfo.getSchoolId(), gradeInfo.getClassroomId()};
                gradeInfo.setSchoolAndClassroomId(Arrays.asList(ids));
            }
            return gradeInfoDtoList;
        }

        return null;
    }

    @Override
    public InitGradeInfoDto initGradeInfo() {
        InitGradeInfoDto initGradeInfoDto = new InitGradeInfoDto();
        initGradeInfoDto.setTeacherInfoList(iTeacherService.getTeacherSelectList());
        initGradeInfoDto.setClassroomDetailInfoList(iClassroomService.getClassroomCascadeSelect());
        return initGradeInfoDto;
    }

    @Override
    public Boolean save(TbGradeDo gradeDo, Integer userId) {
        if (gradeDo == null)
            return false;

        //新增
        if (EmptyUtils.intIsEmpty(gradeDo.getId())) {
            gradeDo.setStatus(Byte.valueOf("1"));
            gradeDo.setCreateId(userId);
            gradeDo.setCreateTime(new Date());
            return tbGradeDoMapper.insert(gradeDo) > 0 ;
        }else {
            gradeDo.setLastUpdateId(userId);
            gradeDo.setLastUpdateTime(new Date());
            return tbGradeDoMapper.updateByPrimaryKeySelective(gradeDo) > 0;
        }
    }

    @Override
    public List<SelectDto> getGradeInfoSelectList() {
        TbGradeDoExample example = new TbGradeDoExample();
        TbGradeDoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbGradeDo> gradeDoList = tbGradeDoMapper.selectByExample(example);
        if (EmptyUtils.listIsNotEmpty(gradeDoList)){
            List<SelectDto> selectDtoList = new ArrayList<>();
            for (TbGradeDo grade:gradeDoList
                 ) {
                SelectDto selectDto = new SelectDto();

                selectDto.setValue(grade.getId());
                selectDto.setLabel(grade.getName());

                selectDtoList.add(selectDto);
            }
            return selectDtoList;
        }
        return null;
    }
}
