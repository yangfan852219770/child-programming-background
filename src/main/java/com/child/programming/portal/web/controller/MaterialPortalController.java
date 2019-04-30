package com.child.programming.portal.web.controller;

import com.child.programming.base.dto.MaterialInfoDto;
import com.child.programming.base.service.IMaterialService;
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
public class MaterialPortalController {
    @Autowired
    private IMaterialService iMaterialService;

    /***
     * 资料详情查看
     * @return
     */
    @RequestMapping("materialGetList")
    public List<MaterialInfoDto> getList(){

     return  iMaterialService.getList(null);
    }
}
