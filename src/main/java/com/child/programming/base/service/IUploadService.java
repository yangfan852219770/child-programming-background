package com.child.programming.base.service;

import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.util.ListUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @return 返回储存路径
     */
    List<ResultDto> uploadFiles(String businessType, MultipartFile[] files, HttpServletRequest request);

    /**
     * 单文件上传
     * @param businessType
     * @param file
     * @param request
     * @return
     */
    ResultDto uploadFile(String businessType, MultipartFile file, HttpServletRequest request);
    /**
     * 单文件上传
     * @param businessType
     * @param file
     * @param request
     * @return 返回文件原始名称+储存路径(目前没用)
     */
    String[] uploadFileReturnOriginNameAndFilePath(String businessType, MultipartFile file, HttpServletRequest request);
    /**
     * 单文件上传
     * @param businessType
     * @param file
     * @param request
     * @return
     */
    String uploadScratch(String businessType, MultipartFile file, HttpServletRequest request);

}
