package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.ClassroomCustomMapper;
import com.child.programming.base.mapper.TbClassroomDtoMapper;
import com.child.programming.base.model.TbClassroomDto;
import com.child.programming.base.model.TbClassroomDtoExample;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.util.EmptyUtil;
import com.child.programming.education.manage.pojo.ClassroomInfoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author：yangfan
 **/

@Service
public class ClassroomServiceImpl implements IClassroomService {
    @Autowired
    private TbClassroomDtoMapper classroomDtoMapper;
    @Autowired
    private ClassroomCustomMapper classroomCustomMapper;


    @Override
    public List<ClassroomInfoPojo> getList(Map<String, Integer> map) {
        return classroomCustomMapper.getListOrBySchoolId(map);
    }

    @Override
    public Boolean save(TbClassroomDto classroomDto, Integer userId) {
        if (null == classroomDto)
            return false;
        if (null == classroomDto.getId()){
            classroomDto.setStatus(Byte.valueOf("1"));
            classroomDto.setCreateId(userId);
            classroomDto.setCreateTime(new Date());
            return classroomDtoMapper.insert(classroomDto) > 0 ? true : false;
        } else {
            classroomDto.setLastUpdateId(userId);
            classroomDto.setLastUpdateTime(new Date());
            return classroomDtoMapper.updateByPrimaryKeySelective(classroomDto) > 0 ? true : false;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtil.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
        ) {
            TbClassroomDto classroomDto = classroomDtoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (null != classroomDto){
                classroomDto.setStatus(Byte.valueOf("0"));
                classroomDto.setLastUpdateId(userId);
                classroomDto.setLastUpdateTime(new Date());
                result += classroomDtoMapper.updateByPrimaryKeySelective(classroomDto);
            }
        }

        return result == idArray.length;
    }
}
