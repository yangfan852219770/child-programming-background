package com.child.programming.base.service;

import com.child.programming.base.model.TbMenuDo;
import com.child.programming.base.dto.MenuInfoDto;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
public interface IMenuService {

    /***
     *
     * 得到menu菜单
     * @return
     */
    List<MenuInfoDto>  getMenuList();


    /**
     * 插入、更新
     * @param menuDo
     * @param userId
     * @return
     */
    Boolean save(TbMenuDo menuDo, Integer userId);

    /**
     * 单个、批量删除
     * @param idArray 删除id集合
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);

    /***
     * 进行授权
     * @param menusIds
     * @param roleToken
     * @param userId
     * @return
     */
    Boolean assignAuthority(String menusIds,String roleToken,Integer userId);
}
