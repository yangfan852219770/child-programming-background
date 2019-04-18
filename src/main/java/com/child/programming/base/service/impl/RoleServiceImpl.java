package com.child.programming.base.service.impl;

import com.child.programming.base.dto.RoleInfoDto;
import com.child.programming.base.mapper.TbRoleDoMapper;
import com.child.programming.base.model.*;
import com.child.programming.base.service.IRoleService;
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
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private TbRoleDoMapper tbRoleDoMapper;

    @Override
    public List<RoleInfoDto> getList(String name) {
        TbRoleDoExample tbRoleDoExample =new TbRoleDoExample();
        TbRoleDoExample.Criteria criteria =tbRoleDoExample.createCriteria();
        if(!EmptyUtils.stringIsEmpty(name))
            criteria.andStatusEqualTo(Byte.valueOf("1")).andNameLike("%"+name+"%");
        else
            criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbRoleDo> tbRoleDos= tbRoleDoMapper.selectByExample(tbRoleDoExample);
        if(!EmptyUtils.listIsEmpty(tbRoleDos))
            return ListUtil.convertElement(tbRoleDos,RoleInfoDto.class);
        else
        return null;
    }

    @Override
    public Boolean save(TbRoleDo tbRoleDo, Integer userId) {
        if (EmptyUtils.objectIsEmpty(tbRoleDo))
            return false;
        //新增
        if (EmptyUtils.intIsEmpty(tbRoleDo.getId())) {
            tbRoleDo.setCreateId(userId);
            tbRoleDo.setCreateTime(new Date());
            tbRoleDo.setStatus(Byte.valueOf("1"));
            return tbRoleDoMapper.insert(tbRoleDo) > 0;
        } else {
            //更新
            tbRoleDo.setLastUpdateId(userId);
            tbRoleDo.setLastUpdateTime(new Date());
            return tbRoleDoMapper.updateByPrimaryKeySelective(tbRoleDo) > 0;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {

        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str : idArray
                ) {
            TbRoleDo tbRoleDo = tbRoleDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (!EmptyUtils.objectIsEmpty(tbRoleDo)) {
                tbRoleDo.setStatus(Byte.valueOf("0"));
                tbRoleDo.setLastUpdateId(userId);
                tbRoleDo.setLastUpdateTime(new Date());
                result += tbRoleDoMapper.updateByPrimaryKeySelective(tbRoleDo);
            }
        }

        return result == idArray.length;
    }

    @Override
    public TbRoleDo selectRoleByToken(String roleToken) {
        TbRoleDoExample tbRoleDoExample =new TbRoleDoExample();
        TbRoleDoExample.Criteria criteria=tbRoleDoExample.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1")).andRoleTokenEqualTo(roleToken);
        List<TbRoleDo> tbRoleDos = tbRoleDoMapper.selectByExample(tbRoleDoExample);
        if(EmptyUtils.listIsEmpty(tbRoleDos))
            return null;
        return tbRoleDos.get(0);
    }
}
