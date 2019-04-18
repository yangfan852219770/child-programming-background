package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.RoleInfoDto;
import com.child.programming.base.model.TbRoleDo;
import com.child.programming.base.service.IRoleService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("role")
@Log4j2
public class RoleController {

    @Autowired
    private IRoleService iRoleService;
    /**
     * 查询角色信息
     * @param name
     * @return
     */
    @RequestMapping("getList")
    public List<RoleInfoDto> getList(@RequestParam(value = "name",required = false)String name){

        return iRoleService.getList(name);

    }
    /**
     * 新增和编辑保存
     * @param session
     * @param tbRoleDo
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbRoleDo tbRoleDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.objectIsEmpty(tbRoleDo)){
            boolean result = iRoleService.save(tbRoleDo,userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }

        return ResultDto.fail();
    }

    /**
     * 删除
     * @param idsStr
     * @param session
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ResultDto delete(@RequestParam(value = "idsStr", required = true)String idsStr,
                            HttpSession session) {
        log.info(idsStr + "删除");
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.stringIsEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            boolean result = iRoleService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }

    @RequestMapping("getRoleByRoleToken")
    public ResultDto getRoleByRoleToken(@RequestParam(value = "roleToken")String roleToken){

        RoleInfoDto roleInfoDto =new RoleInfoDto();
        TbRoleDo tbRoleDo=iRoleService.selectRoleByToken(roleToken);
        if(EmptyUtils.objectIsEmpty(tbRoleDo))
            ResultDto.fail();
        BeanUtils.copyProperties(tbRoleDo,roleInfoDto);
        return ResultDto.success(roleInfoDto);

    }
}
