package com.child.programming.base.util;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.StudentInfoDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Description：
 * @Author：yangfan
 **/
public class HttpSessionUtil {

    /**
     * 获取session中登陆用户信息
     * @param session
     * @return
     */
    public static LoginedUserInfoDto getLoginedUserInfo(HttpSession session){
        return (LoginedUserInfoDto)session.getAttribute(ConstDataUtil.CURRENT_USER);
    }

    public static StudentInfoDto getStudentInfoDto(HttpSession session){
        return (StudentInfoDto) session.getAttribute(ConstDataUtil.CURRENT_STUDENT_USER);
    }

    public static MultipartFile getCurrentUserScratchFile(HttpSession session){
        return (MultipartFile) session.getAttribute(ConstDataUtil.CURRENT_USER_SCRATCH_WORK_FILE);
    }


}
