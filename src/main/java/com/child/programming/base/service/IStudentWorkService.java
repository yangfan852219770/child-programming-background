package com.child.programming.base.service;

import com.child.programming.base.dto.ResultDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author zdp
 * @description: TODO
 */
public interface IStudentWorkService {

    ResultDto uploadScratch(HttpServletRequest request,HttpSession session);
}
