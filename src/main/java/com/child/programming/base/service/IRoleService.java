package com.child.programming.base.service;

import com.child.programming.base.dto.MaterialTypeInfoDto;
import com.child.programming.base.dto.RoleInfoDto;
import com.child.programming.base.model.TbMaterialTypeDo;
import com.child.programming.base.model.TbRoleDo;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
public interface IRoleService {

    /**
     * 查询角色
     * @param name 角色名称
     * @return
     */
    //TODO 目前没有分页
    List<RoleInfoDto> getList(String name);

    /**
     * 插入、更新
     * @param tbRoleDo
     * @param userId
     * @return
     */
    Boolean save(TbRoleDo tbRoleDo, Integer userId);

    /**
     * 单个、批量删除
     * @param idArray 删除id集合
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);
}
