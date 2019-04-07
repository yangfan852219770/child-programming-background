package com.child.programming.base.service.impl;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.mapper.TbStudentWorkDoMapper;
import com.child.programming.base.model.TbStudentWorkDo;
import com.child.programming.base.service.IStudentWorkService;
import com.child.programming.base.service.IUploadService;
import com.child.programming.base.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * @author zdp
 * @description: TODO
 */
@Service
public class StudentWorkServiceImpl implements IStudentWorkService {
    @Autowired
    private IUploadService iUploadService;
    @Autowired
    private TbStudentWorkDoMapper tbStudentWorkDoMapper;
    @Override
    public ResultDto uploadScratch(HttpServletRequest request, HttpSession session) {
        String jsonStr = "";
        MultipartFile file=null;
        String projectName="";
        TbStudentWorkDo tbStudentWorkDo=new TbStudentWorkDo();
        String uploadPath="";

        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if(EmptyUtils.objectIsEmpty(userInfoPojo))
            return  ResultDto.fail("请先登录之后，发布！");

        try {
            jsonStr = GetRequestJsonUtils.getRequestPostStr(request);
        } catch (IOException e) {
            e.printStackTrace();
            return  ResultDto.fail("读取字节流失败");
        }

        if (!EmptyUtils.stringIsEmpty(jsonStr)) {
            @SuppressWarnings("unchecked")
            Map<String, String> map = NewJsonUtils.json2Map(jsonStr);
            // 获取base64的图片编码
            String base64Code = map.get("file");
            projectName=map.get("projectName");
            // 将base64转化
            if (!EmptyUtils.stringIsEmpty(base64Code))
                file = Base64Util.base64ToMultipart(base64Code);
        }
        uploadPath=iUploadService.uploadScratch("scratch",file,request);
        tbStudentWorkDo.setStudentId(userInfoPojo.getId());
        tbStudentWorkDo.setCreateId(userInfoPojo.getId());
        tbStudentWorkDo.setWorkUrl(uploadPath);
        tbStudentWorkDo.setWorkName(projectName);
        tbStudentWorkDo.setStatus(Byte.valueOf("1"));

        return tbStudentWorkDoMapper.insert(tbStudentWorkDo)>0?ResultDto.success("创建成功，快去个人中心中查看吧"):ResultDto.fail("创建失败");
    }
}
