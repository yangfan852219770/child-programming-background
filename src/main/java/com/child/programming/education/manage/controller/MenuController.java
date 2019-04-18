package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.MaterialTypeInfoDto;
import com.child.programming.base.dto.MenuInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbRoleDo;
import com.child.programming.base.service.IMaterialTypeService;
import com.child.programming.base.service.IMenuService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;
    /**
     * 查询菜单列表
     * @return
     */
    @RequestMapping("getList")
    public List<MenuInfoDto> getList(){

        return menuService.getMenuList();
    }

    /**
     * 进行授权
     * @param session
     * @param menuIds
     * @param roleToken
     * @return
     */
    @RequestMapping(value = "assignAuthority", method = RequestMethod.GET)
    public ResultDto assignAuthority(HttpSession session, @RequestParam(value = "menuIds", required = true)String menuIds,@RequestParam(value = "roleToken", required = true)String roleToken){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.stringIsEmpty(menuIds)&&!EmptyUtils.stringIsEmpty(roleToken)){
            boolean result = menuService.assignAuthority(menuIds,roleToken,userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }

        return ResultDto.fail();
    }

}
