package com.child.programming.base.service.impl;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ShiroDto;
import com.child.programming.base.dto.TeacherInfoDto;
import com.child.programming.base.mapper.TbRoleDoMapper;
import com.child.programming.base.mapper.TbTeacherDoMapper;
import com.child.programming.base.model.TbRoleDo;
import com.child.programming.base.model.TbTeacherDo;
import com.child.programming.base.model.TbTeacherDoExample;
import com.child.programming.base.service.ITeacherService;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import com.child.programming.base.util.MD5Util;
import com.child.programming.education.manage.dto.SelectDto;
import org.springframework.beans.BeanUtils;
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
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private TbTeacherDoMapper tbTeacherDoMapper;

    @Autowired
    private TbRoleDoMapper tbRoleDoMapper;


    @Override
    public List<TeacherInfoDto> getList(String name) {
        List<TeacherInfoDto> teacherInfoDtos =new ArrayList<>();
        TbTeacherDoExample example = new TbTeacherDoExample();
        TbTeacherDoExample.Criteria criteria = example.createCriteria();

        example.setOrderByClause("create_time desc");
        if (!EmptyUtils.stringIsEmpty(name))
            criteria.andStatusEqualTo(Byte.valueOf("1")).andNameLike("%" + name + "%");
        else
            criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbTeacherDo> teacherDoList = tbTeacherDoMapper.selectByExample(example);

        if (!EmptyUtils.listIsEmpty(teacherDoList)) {
            for (TbTeacherDo tbTeacherDo : teacherDoList
                    ) {
                TeacherInfoDto teacherInfoDto = new TeacherInfoDto();
                BeanUtils.copyProperties(tbTeacherDo, teacherInfoDto);
                TbRoleDo tbRoleDo=tbRoleDoMapper.selectByPrimaryKey(tbTeacherDo.getRoleId());
                if(!EmptyUtils.objectIsEmpty(tbRoleDo))
                teacherInfoDto.setRoleName(tbRoleDo.getName());
                teacherInfoDtos.add(teacherInfoDto);
            }
            return teacherInfoDtos;
        }
        else
            return null;
    }

    @Override
    public Boolean save(TbTeacherDo teacherDo, Integer userId) {
        if (null == teacherDo)
            return false;
        //插入
        if (null == teacherDo.getId()){
            teacherDo.setPassword(MD5Util.MD5Encode(ConstDataUtil.DEFAULT_PASSWORD));
            teacherDo.setStatus(Byte.valueOf("1"));
            teacherDo.setCreateId(userId);
            teacherDo.setCreateTime(new Date());
            return tbTeacherDoMapper.insert(teacherDo) > 0;
        }else {
            teacherDo.setLastUpdateId(userId);
            teacherDo.setLastUpdateTime(new Date());
            return tbTeacherDoMapper.updateByPrimaryKeySelective(teacherDo) > 0;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtils.arrayIsEmpty(idArray))
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

    @Override
    public Boolean resetPassword(Integer teacherId, Integer userId) {
        TbTeacherDo tbTeacherDo = new TbTeacherDo();
        tbTeacherDo.setPassword(MD5Util.MD5Encode(ConstDataUtil.DEFAULT_PASSWORD));
        tbTeacherDo.setId(teacherId);
        tbTeacherDo.setLastUpdateId(userId);
        tbTeacherDo.setLastUpdateTime(new Date());
        return tbTeacherDoMapper.updateByPrimaryKeySelective(tbTeacherDo) > 0;
    }

    @Override
    public List<SelectDto> getTeacherSelectList() {
        TbTeacherDoExample example = new TbTeacherDoExample();
        TbTeacherDoExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbTeacherDo> teacherDoList = tbTeacherDoMapper.selectByExample(example);
        if (EmptyUtils.listIsNotEmpty(teacherDoList)) {
            List<SelectDto> teacherSelectInfoList = new ArrayList<>();
            for (TbTeacherDo teacher:teacherDoList
                 ) {
                SelectDto selectDto = new SelectDto();
                selectDto.setValue(teacher.getId());
                selectDto.setLabel(teacher.getName());
                teacherSelectInfoList.add(selectDto);
            }
            return teacherSelectInfoList;
        }
        return null;
    }

    @Override
    public LoginedUserInfoDto getTeacherByLoginIdAndPassword(String loginId, String password) {
        LoginedUserInfoDto loginedUserInfoDto =new LoginedUserInfoDto();
        TbTeacherDoExample example = new TbTeacherDoExample();
        TbTeacherDoExample.Criteria criteria = example.createCriteria();

        if (EmptyUtils.stringIsEmpty(loginId)||EmptyUtils.stringIsEmpty(password))
            return  null;


        criteria.andStatusEqualTo(Byte.valueOf("1")).andLoginIdEqualTo(loginId).andPasswordEqualTo(MD5Util.MD5Encode(password));
        List<TbTeacherDo> teacherDoList = tbTeacherDoMapper.selectByExample(example);

        if(EmptyUtils.listIsEmpty(teacherDoList))
        return null;

        BeanUtils.copyProperties(teacherDoList.get(0),loginedUserInfoDto);
       TbRoleDo tbRoleDo= tbRoleDoMapper.selectByPrimaryKey(teacherDoList.get(0).getRoleId());
        if(EmptyUtils.objectIsEmpty(tbRoleDo))
            return null;
         loginedUserInfoDto.setCurrentAuthority(tbRoleDo.getRoleToken());

         return loginedUserInfoDto;
    }

    @Override
    public TbTeacherDo getOneById(Integer teacherId) {
        if (EmptyUtils.intIsNotEmpty(teacherId))
            return tbTeacherDoMapper.selectByPrimaryKey(teacherId);
        return null;
    }
    @Override
    public ShiroDto getTeacherByLoginId(String loginId) {
        ShiroDto shiroDto =new ShiroDto();
        TbTeacherDoExample example = new TbTeacherDoExample();
        TbTeacherDoExample.Criteria criteria = example.createCriteria();

        if (EmptyUtils.stringIsEmpty(loginId))
            return  null;


        criteria.andStatusEqualTo(Byte.valueOf("1")).andLoginIdEqualTo(loginId);
        List<TbTeacherDo> teacherDoList = tbTeacherDoMapper.selectByExample(example);

        if(EmptyUtils.listIsEmpty(teacherDoList))
            return null;

        BeanUtils.copyProperties(teacherDoList.get(0),shiroDto);
        TbRoleDo tbRoleDo= tbRoleDoMapper.selectByPrimaryKey(teacherDoList.get(0).getRoleId());
        if(EmptyUtils.objectIsEmpty(tbRoleDo))
            return null;

        shiroDto.setCurrentAuthority(tbRoleDo.getRoleToken());
        shiroDto.setRoleName(tbRoleDo.getName());
        return shiroDto;
    }
}
