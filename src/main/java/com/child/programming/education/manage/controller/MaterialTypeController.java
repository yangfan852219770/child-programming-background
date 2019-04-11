package com.child.programming.education.manage.controller;
import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.MaterialTypeInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbMaterialTypeDo;
import com.child.programming.base.service.IMaterialTypeService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zdp
 * @description: 资料类别Controller
 */
@RestController
@RequestMapping("materialType")
@Log4j2
public class MaterialTypeController {


    @Autowired
    private IMaterialTypeService iMaterialTypeService;
    /**
     * 查询资料类别信息
     * @param name
     * @return
     */
    @RequestMapping("getList")
    public List<MaterialTypeInfoDto> getList(@RequestParam(value = "name",required = false)String name){

        return iMaterialTypeService.getList(name);

    }
    /**
     * 新增和编辑保存
     * @param session
     * @param tbMaterialTypeDo
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ResultDto save(HttpSession session, @RequestBody TbMaterialTypeDo tbMaterialTypeDo){
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (!EmptyUtils.objectIsEmpty(userInfoPojo) && !EmptyUtils.objectIsEmpty(tbMaterialTypeDo)){
            boolean result = iMaterialTypeService.save(tbMaterialTypeDo,userInfoPojo.getId());
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
            boolean result = iMaterialTypeService.delete(idArray, userInfoPojo.getId());
            if (result)
                return ResultDto.success();
        }
        return ResultDto.fail();
    }
}
