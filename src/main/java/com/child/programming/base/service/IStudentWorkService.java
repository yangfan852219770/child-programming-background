package com.child.programming.base.service;

import com.child.programming.base.dto.MaterialInfoDto;
import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.StudentWorkInfoDto;
import com.child.programming.base.model.TbMaterialDo;
import com.child.programming.base.model.TbStudentWorkDo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zdp
 * @description: StudentWorks Controller
 */
public interface IStudentWorkService {
    /***
     * Scratch release Service
     * @param request
     * @param session
     * @return
     */
    ResultDto uploadScratch(HttpServletRequest request,HttpSession session);

    /**
     * Query Works
     * @param name
     * @return
     */
    //TODO There is no paging at present
    List<StudentWorkInfoDto> getList(String name);

    /**
     * Portal Query Works
     * @return
     */
    //TODO There is no paging at present
    List<StudentWorkInfoDto> getPortalList();
    /**
     * Query Student Work By id
     * @param id
     * @return
     */
    StudentWorkInfoDto getOneById(Integer id);

    Boolean save(TbStudentWorkDo tbStudentWorkDo, Integer userId);

    /**
     * Single、Batch Delete
     * @param idArray Delete id Set
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);


    /**
     * 推送作品
     * @param tbStudentWorkDo
     * @param userId
     * @return
     */
    Boolean pushStudentWork(TbStudentWorkDo tbStudentWorkDo, Integer userId);

    /**
     * 作品保存
     * @param tbStudentWorkDo
     * @param session
     * @return
     */
    ResultDto portalSave(TbStudentWorkDo tbStudentWorkDo,HttpServletRequest request,HttpSession session);

    /**
     * 得到前台登陆者的作品列表
     * @param workName
     * @param session
     * @return
     */
    List<StudentWorkInfoDto> getCurrentStudentWorkGetList(String workName,HttpSession session);
}
