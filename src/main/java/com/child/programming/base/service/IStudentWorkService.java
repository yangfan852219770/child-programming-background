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
     * Insert、Update
     * @param tbStudentWorkDo
     * @param userId
     * @return
     */
    Boolean save(TbStudentWorkDo tbStudentWorkDo, Integer userId);

    /**
     * Single、Batch Delete
     * @param idArray Delete id Set
     * @param userId
     * @return
     */
    Boolean delete(String[] idArray, Integer userId);

    /***
     * 将作品Id 封装到SessionKey 中
     * @param studentWorkId
     * @param session
     * @return
     */
    Boolean sessionKeyUpdate(String studentWorkId, HttpSession session);

    /**
     * 推送作品
     * @param tbStudentWorkDo
     * @param userId
     * @return
     */
    Boolean pushStudentWork(TbStudentWorkDo tbStudentWorkDo, Integer userId);

}
