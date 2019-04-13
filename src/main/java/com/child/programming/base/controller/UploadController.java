package com.child.programming.base.controller;

import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.service.IUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zdp
 * @description: TODO
 */
@RestController
@RequestMapping("upload")
@Log4j2
public class UploadController {

    @Autowired
    private IUploadService iUploadService;
    @RequestMapping("uploadFile")
    public ResultDto uploadFile(String businessType, MultipartFile file, HttpServletRequest request){

         return  iUploadService.uploadFile(businessType,file,request);
    }

    @RequestMapping("uploadFileReturnOriginNameAndFilePath")
    public String[] uploadFileReturnOriginNameAndFilePath(String businessType,MultipartFile file, HttpServletRequest request){

        return  iUploadService.uploadFileReturnOriginNameAndFilePath(businessType,file,request);
    }

}
