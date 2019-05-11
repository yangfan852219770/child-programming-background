package com.child.programming.portal.web.controller;

import com.child.programming.base.dto.MaterialInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.service.IMaterialService;
import com.child.programming.base.util.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */

@RestController
@RequestMapping("portal")
public class MaterialPortalController {
    @Autowired
    private IMaterialService iMaterialService;
    @Value("${IMAGE.BASE.MANAGE.URL}")
    private String baseUrl;
    /***
     * 资料列表
     * @return
     */
    @RequestMapping("materialGetList")
    public List<MaterialInfoDto> getList(Integer typeId){

        List<MaterialInfoDto> list=iMaterialService.getList(typeId);
        if(EmptyUtils.listIsEmpty(list))
            return null;
        for (MaterialInfoDto materialInfoDto:list
                ) {
            materialInfoDto.setFileUrl(baseUrl+materialInfoDto.getFileUrl());
        }
     return  list;
    }
    @RequestMapping("materialSave")
    public ResultDto materialSave(Integer id){

        return  iMaterialService.portalSave( id);
    }

    @RequestMapping("portalVipCheck")
    public ResultDto portalVipCheck(Integer id,String phone){
        return iMaterialService.portalVipCheck(id,phone);
    }
}
