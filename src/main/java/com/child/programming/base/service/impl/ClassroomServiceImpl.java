package com.child.programming.base.service.impl;

import com.child.programming.base.dto.ClassroomDetailInfoDto;
import com.child.programming.base.mapper.ClassroomCustomMapper;
import com.child.programming.base.mapper.TbClassroomDoMapper;
import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.base.model.TbClassroomDoExample;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.service.ISchoolService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.education.manage.dto.CascadeSelectDto;
import com.child.programming.education.manage.dto.SelectDto;
import com.child.programming.education.manage.dto.ValidateClassroomInfoDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description：
 * @Author：yangfan
 **/

@Service
public class ClassroomServiceImpl implements IClassroomService {
    @Autowired
    private TbClassroomDoMapper classroomDoMapper;
    @Autowired
    private ClassroomCustomMapper classroomCustomMapper;

    @Autowired
    private ISchoolService iSchoolService;


    @Override
    public List<ClassroomDetailInfoDto> getList(Map<String, Integer> map) {
        return classroomCustomMapper.getListOrBySchoolId(map);
    }

    @Override
    public Boolean save(TbClassroomDo classroomDo, Integer userId) {
        if (null == classroomDo)
            return false;
        if (null == classroomDo.getId()){
            classroomDo.setStatus(Byte.valueOf("1"));
            classroomDo.setCreateId(userId);
            classroomDo.setCreateTime(new Date());
            return classroomDoMapper.insert(classroomDo) > 0;
        } else {
            classroomDo.setLastUpdateId(userId);
            classroomDo.setLastUpdateTime(new Date());
            return classroomDoMapper.updateByPrimaryKeySelective(classroomDo) > 0;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
        ) {
            TbClassroomDo classroomDo = classroomDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != classroomDo){
                classroomDo.setStatus(Byte.valueOf("0"));
                classroomDo.setLastUpdateId(userId);
                classroomDo.setLastUpdateTime(new Date());
                result += classroomDoMapper.updateByPrimaryKeySelective(classroomDo);
            }
        }

        return result == idArray.length;
    }

    @Override
    public List<ValidateClassroomInfoDto> validateSchoolId(String[] schoolIdArray) {
        if (EmptyUtils.arrayIsEmpty(schoolIdArray))
            return null;
        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> schoolIdList = ListUtil.stringArrayToIntegerList(schoolIdArray);
        if (EmptyUtils.listIsEmpty(schoolIdList))
            return null;
        map.put("schoolIdList", schoolIdList);
        List<ValidateClassroomInfoDto> validateClassroomInfoDtoList = classroomCustomMapper
                .getValidateClassroomInfoListBySchoolId(map);
        return validateClassroomInfoDtoList;
    }

    @Override
    public List<SelectDto> getClassroomSelectBySchoolId(Integer schoolId) {
        if (!EmptyUtils.intIsEmpty(schoolId)){
            TbClassroomDoExample example = new TbClassroomDoExample();
            TbClassroomDoExample.Criteria criteria = example.createCriteria();
            criteria.andSchoolIdEqualTo(schoolId).andStatusEqualTo(Byte.valueOf("1"));
            List<TbClassroomDo> classroomDoList = classroomDoMapper.selectByExample(example);
            List<SelectDto> selectDtoList = new ArrayList<>();
            for (TbClassroomDo classroom:classroomDoList
                 ) {
                SelectDto selectDto = new SelectDto();
                selectDto.setValue(classroom.getId());
                selectDto.setLabel(String.valueOf(classroom.getCode()));
                selectDtoList.add(selectDto);
            }
            return selectDtoList;
        }
        return null;
    }

    @Override
    public List<CascadeSelectDto> getClassroomCascadeSelect() {
        List<SelectDto> schoolList = iSchoolService.getSchoolInfoSelectList();
        if (EmptyUtils.listIsNotEmpty(schoolList)) {
            List<CascadeSelectDto> classroomCascadeList = new ArrayList<>();
            for (SelectDto school:schoolList
                 ) {
                CascadeSelectDto cascadeSelectDto = new CascadeSelectDto();
                //校区信息
                BeanUtils.copyProperties(school, cascadeSelectDto);

                //教室
                List<SelectDto> classroomList = getClassroomSelectBySchoolId(school.getValue());
                if (EmptyUtils.listIsNotEmpty(classroomList)) {
                    cascadeSelectDto.setChildren(ListUtil.convertElement(classroomList, CascadeSelectDto.class));
                    //有教室，才添加
                    classroomCascadeList.add(cascadeSelectDto);
                }
            }
            return classroomCascadeList;
        }
        return null;
    }

    @Override
    public TbClassroomDo getOneById(Integer classroomId) {
        if (EmptyUtils.intIsNotEmpty(classroomId))
            return classroomDoMapper.selectByPrimaryKey(classroomId);
        return null;
    }

    @Override
    public Boolean validateCode(Integer classroomId, Integer code, Integer schoolId) {
        if (EmptyUtils.intIsNotEmpty(code)){
            TbClassroomDoExample example = new TbClassroomDoExample();
            TbClassroomDoExample.Criteria criteria = example.createCriteria();
            if (EmptyUtils.intIsNotEmpty(classroomId))
                criteria.andCodeEqualTo(code).andSchoolIdEqualTo(schoolId).andStatusEqualTo(Byte.valueOf("1")).andIdNotEqualTo(classroomId);
            criteria.andCodeEqualTo(code).andSchoolIdEqualTo(schoolId).andStatusEqualTo(Byte.valueOf("1"));
            List<TbClassroomDo> classroomDoList = classroomDoMapper.selectByExample(example);
            return EmptyUtils.listIsEmpty(classroomDoList);
        }
        return false;
    }
}
