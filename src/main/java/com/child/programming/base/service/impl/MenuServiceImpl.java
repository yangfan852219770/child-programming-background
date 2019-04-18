package com.child.programming.base.service.impl;

import com.child.programming.base.mapper.TbMenuDoMapper;
import com.child.programming.base.model.TbMenuDo;
import com.child.programming.base.model.TbMenuDoExample;
import com.child.programming.base.service.IMenuService;
import com.child.programming.base.dto.MenuInfoDto;
import com.child.programming.base.util.EmptyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        String[] idArray=menusIds.split(",");
        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;

        for (String str:idArray
                ) {
           TbMenuDo tbMenuDo= tbMenuDoMapper.selectByPrimaryKey(Integer.parseInt(str));
           if (!EmptyUtils.objectIsEmpty(tbMenuDo)){
               boolean isAuthor=false;
               String[] authorityArry=tbMenuDo.getAuthority().split(",");
               if(!EmptyUtils.arrayIsEmpty(authorityArry)){
                   List<String> list = new ArrayList<String>();
                   for (int i=0; i<authorityArry.length; i++) {
                       list.add(authorityArry[i]);
                   }
                    for(int j=0;j<list.size();j++){
                       if(list.get(j)==roleToken){
                           isAuthor=true;
                       }
                    }
                    if(!isAuthor){
                        isAuthor=false;
                        list.add(roleToken);
                    }
                   authorityArry=list.toArray(new String[list.size()]);
                   tbMenuDo.setAuthority(StringUtils.join(authorityArry,","));
                   result+= tbMenuDoMapper.updateByPrimaryKeySelective(tbMenuDo);
               }
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
                System.out.println(menuInfoDto.getId()+"/"+tbMenuDo.getPid()+"\n");
                if(menuInfoDto.getId().equals(tbMenuDo.getPid())){
                    MenuInfoDto menuInfoDto1 =new MenuInfoDto();
                    BeanUtils.copyProperties(tbMenuDo,menuInfoDto1);
                    menuInfoDto.setAuthority(tbMenuDo.getAuthority().split(","));
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
