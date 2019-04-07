package com.child.programming.base.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zdp
 * @description: TODO
 */
public interface IUploadService {

    /**
     * 多文件上传
     * @param businessType
     * @param files
     * @param request
     * @return
     */
    String[] uploadFiles(String businessType, MultipartFile[] files, HttpServletRequest request);

    /**
     * 单文件上传
     * @param businessType
     * @param file
     * @param request
     * @return
     */
    String uploadFile(String businessType, MultipartFile file, HttpServletRequest request);
    /**
     * 单文件上传
     * @param businessType
     * @param file
     * @param request
     * @return
     */
    String uploadScratch(String businessType, MultipartFile file, HttpServletRequest request);

}
