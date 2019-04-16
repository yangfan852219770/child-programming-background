package com.child.programming.app.web.controller;

import com.child.programming.app.web.dto.CourseArrange;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.model.TbExperienceCourseDo;
import com.child.programming.base.model.TbShareCircleDo;
import com.child.programming.base.service.IExperienceCourseService;
import com.child.programming.base.util.IDUtil;
import com.child.programming.base.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
/**
 * @Description:    体验课信息
 * @Author:         赵赞峰
 * @Version:        1.0
 */
@RestController
@RequestMapping("app/web/experienceCourse")
public class ExperienceCourseAppController {

@Autowired
private IExperienceCourseService iExperienceCourseService;

    /**
     * @Description:    所有准备开课的体验课列表
     */
    @RequestMapping("getAllExperienceCourse")
    public ResultDto getAllExperienceCourse(){
        List<TbExperienceCourseDo> experienceCourses = iExperienceCourseService.getAllExperienceCourse();
        return ResultDto.success(experienceCourses);
    }

    /**
     * @Description:    获取分享码
     */
    @RequestMapping("getShareCode")
    public ResultDto getShareCode(int studentId,int experienceCourseId){

        //查询是否已分享
        List<TbShareCircleDo> shareCircleDosOld = iExperienceCourseService.getShareCircleByStudentIdAndCourseId(studentId,experienceCourseId);
        if (shareCircleDosOld!=null && shareCircleDosOld.size()>0){
            //已分享过
            return ResultDto.success((Object)shareCircleDosOld.get(0).getEclusiveCode());
        }else {
            //生成随机分享码
            String shareCode = String.valueOf(studentId)+String.valueOf(experienceCourseId)+IDUtil.getLastShareCode();
            System.out.println(shareCode);
            TbShareCircleDo shareCircleDo = new TbShareCircleDo();
            shareCircleDo.setEclusiveCode(shareCode);
            shareCircleDo.setCourseId(experienceCourseId);
            shareCircleDo.setStudentId(studentId);
            shareCircleDo.setShareCounts(0);
            shareCircleDo.setStatus((byte) 1);
            shareCircleDo.setCreateTime(new Date());
            int result = iExperienceCourseService.insertTbShareCircle(shareCircleDo);
            if (result>0){
                return ResultDto.success((Object) shareCode);
            }
        }
        return new ResultDto(ResponseUtil.FAIL_0,"失败");
    }

    /**
     * @Description:    获取分享码
     */
    @RequestMapping("updateShareCodeCount")
    public ResultDto updateShareCodeCount(int experienceCourseId,String shareCodeText){

        if (!StringUtils.isEmpty(shareCodeText)){
            //分享集合
            List<TbShareCircleDo> shareCircleDosOld = iExperienceCourseService.getShareCircleByCourseIdAndShareCode(experienceCourseId,shareCodeText);
            if (shareCircleDosOld!=null && shareCircleDosOld.size()>0){
                //分享次数加一
                TbShareCircleDo shareCircleDo = shareCircleDosOld.get(0);
                shareCircleDo.setShareCounts(shareCircleDo.getShareCounts()+1);
                int result = iExperienceCourseService.updateShareCodeCount(shareCircleDo);
                return ResultDto.success(result);
            }
        }else {
            return ResultDto.success((Object)1);
        }
        return new ResultDto(ResponseUtil.FAIL_0,"失败");
    }

}
