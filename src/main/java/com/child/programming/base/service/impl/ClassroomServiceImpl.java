package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.ClassroomCustomMapper;
import com.child.programming.base.mapper.TbClassroomDoMapper;
import com.child.programming.base.model.TbClassroomDo;
import com.child.programming.base.service.IClassroomService;
import com.child.programming.base.util.EmptyUtil;
import com.child.programming.education.manage.dto.ClassroomInfoDto;
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
    private TbClassroomDoMapper classroomDoMapper;
    @Autowired
    private ClassroomCustomMapper classroomCustomMapper;


    @Override
    public List<ClassroomInfoDto> getList(Map<String, Integer> map) {
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
            return classroomDoMapper.insert(classroomDo) > 0 ? true : false;
        } else {
            classroomDo.setLastUpdateId(userId);
            classroomDo.setLastUpdateTime(new Date());
            return classroomDoMapper.updateByPrimaryKeySelective(classroomDo) > 0 ? true : false;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtil.arrayIsEmpty(idArray))
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
}
