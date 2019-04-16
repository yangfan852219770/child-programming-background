package com.child.programming.base.service.impl;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.MaterialInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.StudentWorkInfoDto;
import com.child.programming.base.mapper.TbStudentWorkDoMapper;
import com.child.programming.base.model.*;
import com.child.programming.base.service.IStudentWorkService;
import com.child.programming.base.service.IUploadService;
import com.child.programming.base.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
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
        tbStudentWorkDo.setWorkUrl(uploadPath);
        tbStudentWorkDo.setWorkName(projectName);
        tbStudentWorkDo.setStatus(Byte.valueOf("1"));
        tbStudentWorkDo.setId(Integer.parseInt(userInfoPojo.getFlexibleProperty()));

        return  this.save(tbStudentWorkDo,userInfoPojo.getId())?ResultDto.success("创建成功，快去个人中心中查看吧"):ResultDto.fail("创建失败");
    }

    @Override
    public List<StudentWorkInfoDto> getList(String name) {
        TbStudentWorkDoExample tbStudentWorkDoExample = new TbStudentWorkDoExample();
        TbStudentWorkDoExample.Criteria criteria = tbStudentWorkDoExample.createCriteria();

        tbStudentWorkDoExample.setOrderByClause("create_time desc");
        if (!EmptyUtils.stringIsEmpty(name))
            criteria.andStatusGreaterThan(Byte.valueOf("0")).andWorkNameLike(name);
        else
           criteria.andStatusGreaterThan(Byte.valueOf("0"));
        List<TbStudentWorkDo> tbStudentWorkDos = tbStudentWorkDoMapper.selectByExample(tbStudentWorkDoExample);
        if (!EmptyUtils.listIsEmpty(tbStudentWorkDos))
            return ListUtil.convertElement(tbStudentWorkDos, StudentWorkInfoDto.class);
        else
            return null;
    }

    @Override
    public Boolean save(TbStudentWorkDo tbStudentWorkDo, Integer userId) {
        if (EmptyUtils.objectIsEmpty(tbStudentWorkDo))
            return false;
        //新增
        if (EmptyUtils.intIsEmpty(tbStudentWorkDo.getId())) {
            tbStudentWorkDo.setCreateId(userId);
            tbStudentWorkDo.setCreateTime(new Date());
            tbStudentWorkDo.setStatus(Byte.valueOf("1"));
            return tbStudentWorkDoMapper.insert(tbStudentWorkDo) > 0;
        } else {
            //更新
            tbStudentWorkDo.setLastUpdateId(userId);
            tbStudentWorkDo.setLastUpdateTime(new Date());
            return tbStudentWorkDoMapper.updateByPrimaryKeySelective(tbStudentWorkDo) > 0;
        }
    }

    @Override
    public Boolean delete(String[] idArray, Integer userId) {
        if (EmptyUtils.arrayIsEmpty(idArray))
            return false;

        int result = 0;
        for (String str:idArray
                ) {
            TbStudentWorkDo studentWorkDo = tbStudentWorkDoMapper.selectByPrimaryKey(Integer.parseInt(str));
            if (!EmptyUtils.objectIsEmpty(studentWorkDo)){
                studentWorkDo.setStatus(Byte.valueOf("0"));
                studentWorkDo.setLastUpdateId(userId);
                studentWorkDo.setLastUpdateTime(new Date());
                result += tbStudentWorkDoMapper.updateByPrimaryKeySelective(studentWorkDo);
            }
        }

        return result == idArray.length;
    }

    @Override
    public Boolean sessionKeyUpdate(String studentWorkId, HttpSession session) {
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if(EmptyUtils.objectIsEmpty(userInfoPojo))
            return false;
        userInfoPojo.setFlexibleProperty(studentWorkId);
        session.setAttribute(ConstDataUtil.CURRENT_USER,userInfoPojo);
        return  true;
    }

    @Override
    public Boolean pushStudentWork(TbStudentWorkDo tbStudentWorkDo, Integer userId) {
       if(EmptyUtils.objectIsEmpty(tbStudentWorkDo))
           return  false;
       tbStudentWorkDo.setStatus(Byte.valueOf("2"));
       tbStudentWorkDo.setTeacherId(userId);
      return this.save(tbStudentWorkDo,userId);
    }
}
