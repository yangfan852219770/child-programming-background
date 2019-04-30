package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.MaterialInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbMaterialDo;
import com.child.programming.base.service.IMaterialService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zdp
 * @description: 资料Controller
 */
@RestController
@RequestMapping("material")
@Log4j2
public class MaterialController {
    @Autowired
    private IMaterialService iMaterialService;

    /**
     * 查询资料信息
     * @param materialTypeId
     * @return
     */
    @RequestMapping("getList")
    public List<MaterialInfoDto> getList(@RequestParam(value = "materialTypeId",required = false)Integer materialTypeId){

        return iMaterialService.getList(materialTypeId);

    }
    /**
     * 新增和编辑保存
     * @param tbMaterialDo
     * @param session
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbMaterialDo tbMaterialDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.objectIsEmpty(tbMaterialDo)){
            boolean result = iMaterialService.save(tbMaterialDo,userInfoPojo.getId());
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
            boolean result = iMaterialService.delete(idArray, userInfoPojo.getId());
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
    @RequestMapping(value = "push", method = RequestMethod.GET)
    public ResultDto push(@RequestParam(value = "idsStr", required = true)String idsStr,
                          @RequestParam(value = "status", required = true)String status, HttpSession session) {
        log.info(idsStr + "推送");
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.stringIsEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            boolean result = iMaterialService.push(idArray,status, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }
}
