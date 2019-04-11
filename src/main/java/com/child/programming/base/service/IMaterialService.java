package com.child.programming.base.service;

import com.child.programming.base.dto.MaterialInfoDto;
import com.child.programming.base.model.TbMaterialDo;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
public interface IMaterialService {
    /**
     * 查询资料
     * @param materialTypeId 资料id
     * @return
     */
    //TODO 目前没有分页
    List<MaterialInfoDto> getList(Integer materialTypeId);

    /**
     * 插入、更新
     * @param materialDo
     * @param userId
     * @return
     */
    Boolean save(TbMaterialDo materialDo, Integer userId);

    /**
     * 单个、批量删除
     * @param idArray 删除id集合
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);
}
