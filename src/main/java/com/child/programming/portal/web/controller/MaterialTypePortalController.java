package com.child.programming.portal.web.controller;

import com.child.programming.base.dto.MaterialInfoDto;
import com.child.programming.base.dto.MaterialTypeInfoDto;
import com.child.programming.base.service.IMaterialService;
import com.child.programming.base.service.IMaterialTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */

@RestController
@RequestMapping("portal")
public class MaterialTypePortalController {
    @Autowired
    private IMaterialTypeService iMaterialtypeService;

    /***
     * 资料类别列表
     * @return
     */
    @RequestMapping("materialTypeGetList")
    public List<MaterialTypeInfoDto> getList(){
     return  iMaterialtypeService.getList("");
    }
}
