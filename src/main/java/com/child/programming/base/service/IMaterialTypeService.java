package com.child.programming.base.service;

import com.child.programming.base.dto.MaterialTypeInfoDto;
import com.child.programming.base.model.TbMaterialTypeDo;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
public interface IMaterialTypeService {

    /**
     * 查询资料类别
     * @param name 资料类别名称
     * @return
     */
    //TODO 目前没有分页
    List<MaterialTypeInfoDto> getList(String name);

    /**
     * 插入、更新
     * @param tbMaterialTypeDo
     * @param userId
     * @return
     */
    Boolean save(TbMaterialTypeDo tbMaterialTypeDo, Integer userId);

    /**
     * 单个、批量删除
     * @param idArray 删除id集合
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);
}
