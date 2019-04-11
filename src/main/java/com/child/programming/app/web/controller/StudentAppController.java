package com.child.programming.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.child.programming.app.web.dto.TokenDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbSignUpExperienceCourseDo;
import com.child.programming.base.model.TbStudentDo;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.service.IStudentService;
import com.child.programming.base.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("app/web/student")
public class StudentAppController {

    @Value("${wx.appid}")
    private String wxAppid;
    @Value("${wx.secret}")
    private String wxSecret;

    @Value("${FTP.ADDRESS}")
    private String host;
    // 端口
    @Value("${FTP.PORT}")
    private int port;
    // ftp用户名
    @Value("${FTP.USERNAME}")
    private String userName;
    // ftp用户密码
    @Value("${FTP.PASSWORD}")
    private String passWord;
    // 文件在服务器端保存的主目录
    @Value("${FTP.BASEPATH}")
    private String basePath;
    // 访问图片时的基础url
    @Value("${IMAGE.BASE.URL}")
    private String baseUrl;

    @Autowired
    private IStudentService iStudentService;

    @RequestMapping("getOpenId")
    public ResultDto getOpenId(String code){
        if (StringUtils.isEmpty(code)) {
            return new ResultDto(ResponseUtil.FAIL_0,"code为空");
        }
        Object openId = null;
        String params = "appid=" + wxAppid + "&secret=" + wxSecret + "&js_code=" + code + "&grant_type=authorization_code";
        //发送请求
        String  returnvalue= WeChatUtil.sendGet(WeChatUtil.getOpenIdURL,params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(returnvalue);
        if (!json.isEmpty()){
            openId = json.get("openid");
        }
        if (openId!=null){
            return ResultDto.success(openId);
        }
        return new ResultDto(ResponseUtil.FAIL_0,"获取openId失败");
    }


    @RequestMapping("addStudent")
    public ResultDto addUser(TbStudentDo studentDto){
        TbStudentDo studentDtoOld = iStudentService.getStudentByOpenId(studentDto.getOpenid());
        WXTokenUtil wxTokenUtil = new WXTokenUtil();
        TokenDto tokenDto = new TokenDto();
        studentDto.setId(studentDtoOld.getId());
        studentDto.setOpenid(studentDtoOld.getOpenid());
        tokenDto.setAccessToken(getAccessToken());
        String token = wxTokenUtil.generateToken(tokenDto);
        studentDto.setAccentToken(token);
//        根据openId判断
        if (studentDtoOld!=null){
            //更新
            studentDto.setLastUpdateTime(new Date());
            int result = iStudentService.updateStudentByOpenId(studentDto);
            if (result>0){
                return ResultDto.success(studentDto);
            }
        }else{
            //添加
            studentDto.setCreateTime(new Date());
            int result = iStudentService.addStudent(studentDto);
            if (result>0){
                return ResultDto.success(studentDto);
            }
        }
        return new ResultDto(ResponseUtil.FAIL_0,"添加用户失败");
    }

    @RequestMapping("addStudentInfo")
    public ResultDto addUser(@RequestParam("file") MultipartFile uploadFile,TbStudentDo studentDto){
        try {
            //1、给上传的图片生成新的文件名
            //1.1获取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtil.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //1.3生成文件在服务器端存储的子目录
            String filePath = DateUtil.DateToString(new Date(),"/yyyy/MM/dd");
            //3、把图片上传到图片服务器
            //3.1获取上传的io流
            InputStream input = uploadFile.getInputStream();
            //3.2调用FtpUtil工具类进行上传
            boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath,"/images"+filePath, newName, input);
            input.close();
            if (result){
                //2、把前端输入信息，包括图片的url保存到数据库
                studentDto.setPhotoUrl(baseUrl+filePath+"/"+newName);
                int updateStudentResult = iStudentService.updateStudentByOpenId(studentDto);
                System.out.println(updateStudentResult);
                if (updateStudentResult>0){
                    TbStudentDo studentDtoOld = iStudentService.getStudentByOpenId(studentDto.getOpenid());
                    return ResultDto.success(studentDtoOld);
                }
            }
        } catch (Exception e) {
            return new ResultDto(ResponseUtil.FAIL_0,"添加用户信息失败");
        }
        return new ResultDto(ResponseUtil.FAIL_0,"添加用户信息失败");
    }

    @RequestMapping("getStudentByOpenId")
    public ResultDto getUserByOpenId(String openId){
        if (StringUtils.isEmpty(openId)) {
            return new ResultDto(ResponseUtil.FAIL_0,"openId为空");
        }
        TbStudentDo studentDto = iStudentService.getStudentByOpenId(openId);
        if (studentDto!=null){
            return ResultDto.success(studentDto);
        }
        return new ResultDto(ResponseUtil.FAIL_0,"获取用户失败");
    }

    public String getAccessToken(){
        //请求参数
        String params = "appid=" + wxAppid + "&secret=" + wxSecret+ "&grant_type=client_credential";
        //发送请求
        String  returnvalue=WeChatUtil.sendGet(WeChatUtil.getAccessTokenURL,params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(returnvalue);
        System.out.println(json);//{"access_token":"19_VElztXw3RmcNbtNy_GpQgZiwp-2BzQKPrxzIStWCyaeEXCLCVDmDyD8hUtiP5nYbwgbpv0QHgmRikjbMTG7NiT9lrxOypIMlFFrIBubRf_2alOE2wc2TYJA1UAvfs0ZGg67w006u69UB5QWEZAEgAAAIJN","expires_in":7200}
        System.out.println(json.get("access_token"));
        if (!StringUtils.isEmpty(json)){
            return (String) json.get("access_token");
        }
        return null;
    }

    @RequestMapping("signUpCourse")
    public ResultDto signUpCourse(int gradeId,int studentId,String druingDate){
        TbStudentSignUpDo studentSignUpDo = new TbStudentSignUpDo();
        studentSignUpDo.setStudentId(studentId);
        studentSignUpDo.setClassId(gradeId);
        studentSignUpDo.setSignUpTime(new Date());
        studentSignUpDo.setIsPayment((byte) 0);
        studentSignUpDo.setStatus((byte) 1);
        try {
            int index = druingDate.indexOf("~");
            String startDate = druingDate.substring(0,index);
            System.out.println(startDate);
            if (DateUtil.compareDate(DateUtil.StringToDate(startDate,"yyyy-MM-dd"),new Date())>=0){
                studentSignUpDo.setIsHalfway((byte) 1);
            }else {
                studentSignUpDo.setIsHalfway((byte) 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        studentSignUpDo.setStatus((byte) 1);
        int result = iStudentService.signUpCourse(studentSignUpDo);
        if (result>0){
            return ResultDto.success(result);
        }
        return new ResultDto(ResponseUtil.FAIL_0,"报名失败");
    }


    @RequestMapping("signUpExperienceCourse")
    public ResultDto signUpExperienceCourse(int experienceCourseId,int studentId){
        TbSignUpExperienceCourseDo signUpExperienceCourseDo = new TbSignUpExperienceCourseDo();
        signUpExperienceCourseDo.setStudentId(studentId);
        signUpExperienceCourseDo.setExperienceCourseId(experienceCourseId);
        signUpExperienceCourseDo.setStatus((byte) 1);
        signUpExperienceCourseDo.setCreateId(studentId);
        signUpExperienceCourseDo.setCreateTime(new Date());
        int result = iStudentService.signUpExperienceCourse(signUpExperienceCourseDo);
        if (result>0){
            return ResultDto.success(result);
        }
        return new ResultDto(ResponseUtil.FAIL_0,"报名失败");

    }

}
