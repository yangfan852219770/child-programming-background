package com.child.programming.app.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.child.programming.app.web.dto.TokenDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbSignUpExperienceCourseDo;
import com.child.programming.base.model.TbStudentDo;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.model.TbSuggestionDo;
import com.child.programming.base.service.IStudentService;
import com.child.programming.base.service.ISuggestionService;
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
import java.util.List;

/**
 * @Description: 学生信息
 * @Author: 赵赞峰
 * @Version: 1.0
 */
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
    @Autowired
    private ISuggestionService iSuggestionService;

    /**
     * @Description: 获取OpenId
     */
    @RequestMapping("getOpenId")
    public ResultDto getOpenId(String code) {
        if (StringUtils.isEmpty(code)) {
            return new ResultDto(ResponseUtil.FAIL_0, "code为空");
        }
        Object openId = null;
        String params = "appid=" + wxAppid + "&secret=" + wxSecret + "&js_code=" + code + "&grant_type=authorization_code";
        //发送请求
        String returnvalue = WeChatUtil.sendGet(WeChatUtil.getOpenIdURL, params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(returnvalue);
        if (!json.isEmpty()) {
            openId = json.get("openid");
        }
        if (openId != null) {
            return ResultDto.success(openId);
        }
        return new ResultDto(ResponseUtil.FAIL_0, "获取openId失败");
    }

    /**
     * @Description: 根据openId添加或修改学生信息
     */
    @RequestMapping("addStudent")
    public ResultDto addUser(TbStudentDo studentDto) {
        TbStudentDo studentDtoOld = iStudentService.getStudentByOpenId(studentDto.getOpenid());
        WXTokenUtil wxTokenUtil = new WXTokenUtil();
        TokenDto tokenDto = new TokenDto();
//        studentDto.setId(studentDtoOld.getId());
//        studentDto.setOpenid(studentDtoOld.getOpenid());
//        tokenDto.setAccessToken(getAccessToken());
//        String token = wxTokenUtil.generateToken(tokenDto);
//        studentDto.setAccentToken(token);
//        根据openId判断
        if (studentDtoOld != null) {
            //更新
            studentDto.setId(studentDtoOld.getId());
            studentDto.setOpenid(studentDtoOld.getOpenid());
//            tokenDto.setAccessToken(getAccessToken());
//            String token = wxTokenUtil.generateToken(tokenDto);
//            studentDto.setAccentToken(token);
            studentDto.setLastUpdateTime(new Date());
            studentDto.setStatus(studentDtoOld.getStatus());
            int result = iStudentService.updateStudentByOpenId(studentDto);
            if (result > 0) {
                return ResultDto.success(studentDto);
            }
        } else {
            //添加
            //studentDto.setId(studentDto.getId());
            studentDto.setOpenid(studentDto.getOpenid());
//            tokenDto.setAccessToken(getAccessToken());
//            String token = wxTokenUtil.generateToken(tokenDto);
//            studentDto.setAccentToken(token);
            studentDto.setCreateTime(new Date());
            studentDto.setStatus((byte) 1);
            int result = iStudentService.addStudent(studentDto);
            if (result > 0) {
                return ResultDto.success(studentDto);
            }
        }
        return new ResultDto(ResponseUtil.FAIL_0, "添加用户失败");
    }

    /**
     * @Description: 添加学生相信信息
     */
    @RequestMapping("addStudentInfo")
    public ResultDto addUser(@RequestParam("file") MultipartFile uploadFile, TbStudentDo studentDto) {
        TbStudentDo studentDtoOld = iStudentService.getStudentByOpenId(studentDto.getOpenid());
        if (studentDtoOld!=null){
            iStudentService.updateStudentByOpenId(studentDto);
            return ResultDto.success(studentDto);
        }
        try {
            //1、给上传的图片生成新的文件名
            //1.1获取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtil.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //1.3生成文件在服务器端存储的子目录
            String filePath = DateUtil.DateToString(new Date(), "/yyyy/MM/dd");
            //3、把图片上传到图片服务器
            //3.1获取上传的io流
            InputStream input = uploadFile.getInputStream();
            //3.2调用FtpUtil工具类进行上传
            boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, "/images" + filePath, newName, input);
            input.close();
            if (result) {
                //2、把前端输入信息，包括图片的url保存到数据库
                studentDto.setPhotoUrl(baseUrl + filePath + "/" + newName);
                studentDto.setStatus((byte) 1);
                int addStudentResult = iStudentService.addStudent(studentDto);
                if (addStudentResult > 0) {
//                    TbStudentDo studentDtoOld = iStudentService.getStudentByOpenId(studentDto.getOpenid());
                    return ResultDto.success(studentDto);
                }
            }
        } catch (Exception e) {
            return new ResultDto(ResponseUtil.FAIL_0, "添加用户信息失败");
        }
        return new ResultDto(ResponseUtil.FAIL_0, "添加用户信息失败");
    }

    /**
     * @Description: 据openId查询学生信息
     */
    @RequestMapping("getStudentByOpenId")
    public ResultDto getUserByOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            return new ResultDto(ResponseUtil.FAIL_0, "openId为空");
        }
        TbStudentDo studentDto = iStudentService.getStudentByOpenId(openId);
        if (studentDto != null) {
            return ResultDto.success(studentDto);
        }
        return new ResultDto(ResponseUtil.FAIL_0, "获取用户失败");
    }

    /**
     * @Description: 获取ccessToken
     */
    public String getAccessToken() {
        //请求参数
        String params = "appid=" + wxAppid + "&secret=" + wxSecret + "&grant_type=client_credential";
        //发送请求
        String returnvalue = WeChatUtil.sendGet(WeChatUtil.getAccessTokenURL, params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(returnvalue);
        System.out.println(json);//{"access_token":"19_VElztXw3RmcNbtNy_GpQgZiwp-2BzQKPrxzIStWCyaeEXCLCVDmDyD8hUtiP5nYbwgbpv0QHgmRikjbMTG7NiT9lrxOypIMlFFrIBubRf_2alOE2wc2TYJA1UAvfs0ZGg67w006u69UB5QWEZAEgAAAIJN","expires_in":7200}
        System.out.println(json.get("access_token"));
        if (!StringUtils.isEmpty(json)) {
            return (String) json.get("access_token");
        }
        return null;
    }

    @RequestMapping("getWXAccessToken")
    public ResultDto getWXAccessToken() {
        String accessToken = getAccessToken();
        return ResultDto.success((Object) accessToken);
    }

    /**
     * @Description: 学生报名课程
     */
    @RequestMapping("signUpCourse")
    public ResultDto signUpCourse(int gradeId, int studentId, String druingDate) {
        int index = druingDate.indexOf("~");
        String startDate = druingDate.substring(0, index);
        String endDate = druingDate.substring(index+1, druingDate.length());
        if (DateUtil.compareDate(DateUtil.StringToDate(endDate, "yyyy-MM-dd"), new Date()) >= 0) {
            return ResultDto.success((Object)3);
        }
        //是否已经报名过
        List<TbStudentSignUpDo> studentSignUpDoList = iStudentService.getStudentSignUpByClassIdAndStudentId(gradeId, studentId);
        if (studentSignUpDoList != null && studentSignUpDoList.size() > 0) {
            //已经报名过此课程
            return ResultDto.success((Object)2);
        } else {
            TbStudentSignUpDo studentSignUpDo = new TbStudentSignUpDo();
            studentSignUpDo.setStudentId(studentId);
            studentSignUpDo.setGradeId(gradeId);
            studentSignUpDo.setSignUpTime(new Date());
            studentSignUpDo.setIsPayment((byte) 0);
            studentSignUpDo.setStatus((byte) 1);
            try {
                if (DateUtil.compareDate(DateUtil.StringToDate(startDate, "yyyy-MM-dd"), new Date()) >= 0) {
                    studentSignUpDo.setIsHalfway((byte) 1);
                } else {
                    studentSignUpDo.setIsHalfway((byte) 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            studentSignUpDo.setStatus((byte) 1);
            int result = iStudentService.signUpCourse(studentSignUpDo);
            if (result > 0) {
                return ResultDto.success((Object)1);
            }
        }
        return new ResultDto(ResponseUtil.FAIL_0, "报名失败");
    }

    /**
     * @Description: 报名体验课
     */
    @RequestMapping("signUpExperienceCourse")
    public ResultDto signUpExperienceCourse(int experienceCourseId,String phone) {
        //查询是否已经报名
        List<TbSignUpExperienceCourseDo> signUpExperienceCourseDos = iStudentService.getsignUpExperienceCourseByExperienceCourseIdAndPhone(experienceCourseId,phone);
        if (signUpExperienceCourseDos!=null && signUpExperienceCourseDos.size()>0){
            return ResultDto.success((Object)2);
        }else{
            TbSignUpExperienceCourseDo signUpExperienceCourseDo = new TbSignUpExperienceCourseDo();
            signUpExperienceCourseDo.setPhone(phone);
            signUpExperienceCourseDo.setExperienceCourseId(experienceCourseId);
            signUpExperienceCourseDo.setStatus((byte) 1);
            signUpExperienceCourseDo.setIsPayment((byte) 0);
            signUpExperienceCourseDo.setSignUpTime(new Date());
            signUpExperienceCourseDo.setCreateTime(new Date());
            int result = iStudentService.signUpExperienceCourse(signUpExperienceCourseDo);
            if (result > 0) {
                return ResultDto.success((Object)1);
            }
        }
        return new ResultDto(ResponseUtil.FAIL_0, "报名失败");

    }


    /**
     * @Description: 是否收藏此课程
     */
    @RequestMapping("isCollectCourse")
    public ResultDto isCollectCourse(int courseId, int studentId) {
        Boolean result = iStudentService.isCollectCourse(courseId, studentId);
        return ResultDto.success(result);
    }


    /**
     * @Description: 是否收藏此课程
     */
    @RequestMapping("updateCollectCourse")
    public ResultDto updateCollectCourse(int courseId, int studentId, Boolean flag) {
        if (flag) {
            //收藏
            int result = iStudentService.saveCollectCourse(courseId, studentId);
            if (result > 0) {
                return ResultDto.success(true);
            }
        } else {
            //取消
            int result = iStudentService.deleteCollectCourse(courseId, studentId);
            if (result > 0) {
                return ResultDto.success(false);
            }
        }
        return new ResultDto(ResponseUtil.FAIL_0, "报名失败");
    }

    /**
     * @Description: 自己的意见反馈
     */
    @RequestMapping("getSuggesstionByStudentId")
    public ResultDto getSuggesstionByStudentId(int studentId) {
       List<TbSuggestionDo> suggestionDos = iSuggestionService.getSuggesstionByStudentId(studentId);
        return ResultDto.success(suggestionDos);
    }

    /**
     * @Description: 自己的意见反馈
     */
    @RequestMapping("saveSuggesstion")
    public ResultDto saveSuggesstion(int studentId,String content) {
        TbSuggestionDo tbSuggestionDo = new TbSuggestionDo();
        tbSuggestionDo.setCommentText(content);
        tbSuggestionDo.setCreateId(studentId);
        tbSuggestionDo.setCreateTime(new Date());
        tbSuggestionDo.setStatus((byte) 1);
        Boolean result = iSuggestionService.saveSuggesstion(tbSuggestionDo);
        return ResultDto.success(result);
    }


}
