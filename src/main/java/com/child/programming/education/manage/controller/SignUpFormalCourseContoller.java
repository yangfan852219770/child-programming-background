package com.child.programming.education.manage.controller;

import com.child.programming.base.dto.LoginedUserInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.SignUpFormalCourseInfoDto;
import com.child.programming.base.model.TbPaymentRecordDo;
import com.child.programming.base.model.TbStudentSignUpDo;
import com.child.programming.base.service.IPaymentRecordService;
import com.child.programming.base.service.ISignUpFormalCourseService;
import com.child.programming.base.util.EmptyUtils;
import com.child.programming.base.util.HttpSessionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：正式课报名管理
 * @Author：yangfan
 **/

@RestController
@RequestMapping("/signUpFormalCourse")
@Log4j2
public class SignUpFormalCourseContoller {
    @Autowired
    private ISignUpFormalCourseService iSignUpFormalCourseService;
    @Autowired
    private IPaymentRecordService iPaymentRecordService;

    /**
     * 学生报名正式课列表
     * @param studentName 学生姓名
     * @param isPayment 是否缴费
     * @return
     */
    @RequestMapping(value = "getList", method = RequestMethod.GET)
    public List<SignUpFormalCourseInfoDto> getList(@RequestParam(value = "studentName", required = false)String studentName,
                                                   @RequestParam(value = "isPayment", required = false)String isPayment){
        Map map = new HashMap();
        map.put("studentName", studentName);
        map.put("isPayment", isPayment);
        return iSignUpFormalCourseService.getList(map);
    }

    /**
     * 逻辑删除
     * @param idsStr
     * @param session
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public ResultDto delete(@RequestParam(value = "idsStr", required = true)String idsStr,
                            HttpSession session) {
        log.info(idsStr + "删除");

        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null != userInfoPojo && EmptyUtils.stringIsNotEmpty(idsStr)) {
            String[] idArray = idsStr.split(",");
            if (!EmptyUtils.arrayIsEmpty(idArray)){
                boolean result = iSignUpFormalCourseService.delete(idArray, userInfoPojo.getId());
                if (result)
                    return ResultDto.success();
            }
        }
        return ResultDto.fail();
    }

    /**
     * 缴费
     * @param signUpId
     * @param courseMoney
     * @param courseId
     * @param studentId
     * @param session
     * @return
     */
    @RequestMapping(value = "payMoney", method = RequestMethod.GET)
    public ResultDto payMoney(@RequestParam(value = "signUpId", required = true)Integer signUpId,
                              @RequestParam(value = "courseMoney", required = true)Double courseMoney,
                              @RequestParam(value = "courseId", required = true)Integer courseId,
                              @RequestParam(value = "studentId", required = true)Integer studentId,
                              HttpSession session){
        if (null == signUpId || null == courseMoney || null == courseId)
            return ResultDto.fail("参数错误!");
        LoginedUserInfoDto userInfoPojo = HttpSessionUtil.getLoginedUserInfo(session);
        if (null == userInfoPojo)
            return ResultDto.fail("请先登陆!");
        // 插入缴费记录
        TbPaymentRecordDo paymentRecordDo = new TbPaymentRecordDo();

        paymentRecordDo.setStudentId(studentId);
        paymentRecordDo.setCourseId(courseId);
        paymentRecordDo.setPayMoney(courseMoney);
        paymentRecordDo.setType(Byte.valueOf("1"));

        boolean saveResult = iPaymentRecordService.insert(paymentRecordDo, userInfoPojo.getId());
        // 更改报名缴费状态
        if (saveResult){
            TbStudentSignUpDo studentSignUpDo = new TbStudentSignUpDo();
            studentSignUpDo.setId(signUpId);
            studentSignUpDo.setIsPayment(Byte.valueOf("1"));
            boolean result = iSignUpFormalCourseService.update(studentSignUpDo, userInfoPojo.getId());
            if (result)
                return ResultDto.success("缴费成功");
        }
        return ResultDto.fail("缴费失败!");
    }
}
