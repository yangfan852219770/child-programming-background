package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbMenuDoMapper;
import com.child.programming.base.model.TbMenuDo;
import com.child.programming.base.model.TbMenuDoExample;
import com.child.programming.base.service.IMenuService;
import com.child.programming.base.dto.MenuInfoDto;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zdp
 * @description: Menu菜单实现
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private TbMenuDoMapper tbMenuDoMapper;

    @Override
    public List<MenuInfoDto> getMenuList() {
        List<MenuInfoDto> menuInfoDtos =new ArrayList<>();
        TbMenuDoExample tbMenuDoExample =new TbMenuDoExample();
        TbMenuDoExample.Criteria criteria= tbMenuDoExample.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<TbMenuDo> tbMenuDos =tbMenuDoMapper.selectByExample(tbMenuDoExample);


        for (TbMenuDo  tbMenuDo:tbMenuDos
             ) {
          if(tbMenuDo.getPid()==0){
              MenuInfoDto menuInfoDto =new MenuInfoDto();
              BeanUtils.copyProperties(tbMenuDo,menuInfoDto);
              menuInfoDto.setAuthority(tbMenuDo.getAuthority().split(","));
              menuInfoDtos.add(menuInfoDto);
          }
        }
        return getMenuInfoDtoList(tbMenuDos,menuInfoDtos);
    }

    @Override
    public Boolean save(TbMenuDo menuDo, Integer userId) {
        return null;
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        return null;
    }

    @Override
    public Boolean assignAuthority(String menusIds, String roleToken, Integer userId) {

        //先清空当前用户以前的权限
        TbMenuDoExample  example =new TbMenuDoExample();
        TbMenuDoExample.Criteria criteria =example.createCriteria();
        criteria.andStatusEqualTo(Byte.valueOf("1"));

       List<TbMenuDo> tbMenuDoLit=tbMenuDoMapper.selectByExample(example);
        if(EmptyUtils.listIsEmpty(tbMenuDoLit))
            return  false;
        for (TbMenuDo tbMenuDo: tbMenuDoLit) {
            if(!EmptyUtils.stringIsEmpty(tbMenuDo.getAuthority())){
                String[] dbAuthorArry=tbMenuDo.getAuthority().split(",");
                if(dbAuthorArry.length<=0)
                    continue;
               List<String> authorArryList = ListUtil.ArrayToList(dbAuthorArry);
               if(authorArryList.size()<=0)
                   continue;
                for(int i=0;i<authorArryList.size();i++){
                    if(authorArryList.get(i).equals(roleToken)){
                        authorArryList.remove(i);
                    }
                }

                tbMenuDo.setAuthority(StringUtils.join(authorArryList.toArray(),","));
                tbMenuDoMapper.updateByPrimaryKeySelective(tbMenuDo);
            }
        }
        //全部授权
        if(EmptyUtils.stringIsEmpty(menusIds))
            return true;
        //重新赋值权限
        String[] idArray=menusIds.split(",");
        if (EmptyUtils.arrayIsEmpty(idArray)||EmptyUtils.stringIsEmpty(roleToken))
            return false;
        int result = 0;
        for (String str:idArray
                ) {
           TbMenuDo tbMenuDo= tbMenuDoMapper.selectByPrimaryKey(Integer.parseInt(str));
           if (!EmptyUtils.objectIsEmpty(tbMenuDo)){
               if(EmptyUtils.stringIsEmpty(tbMenuDo.getAuthority()))
                   tbMenuDo.setAuthority(roleToken);
               else
                   tbMenuDo.setAuthority(tbMenuDo.getAuthority()+","+roleToken);

               result+=tbMenuDoMapper.updateByPrimaryKeySelective(tbMenuDo);
               }
           }

        return  result==idArray.length;
    }

    private List<MenuInfoDto>  getMenuInfoDtoList(List<TbMenuDo> tbMenuDos,List<MenuInfoDto> menuInfoDtos){

        for (MenuInfoDto menuInfoDto:menuInfoDtos
                ) {
            List<MenuInfoDto> menuInfoDtos1 =new ArrayList<>();
            for (TbMenuDo  tbMenuDo:tbMenuDos
                    ) {
                if(menuInfoDto.getId().equals(tbMenuDo.getPid())){
                    MenuInfoDto menuInfoDto1 =new MenuInfoDto();
                    BeanUtils.copyProperties(tbMenuDo,menuInfoDto1);
                    menuInfoDto1.setAuthority(tbMenuDo.getAuthority().split(","));
                    menuInfoDtos1.add(menuInfoDto1);
                }
            }

            menuInfoDto.setChildren(getMenuInfoDtoList(tbMenuDos,menuInfoDtos1));
        }

        if(EmptyUtils.listIsEmpty(menuInfoDtos))
            return menuInfoDtos;

        return menuInfoDtos;
    }
}
