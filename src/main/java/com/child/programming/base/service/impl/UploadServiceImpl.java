package com.child.programming.base.service.impl;

import com.child.programming.base.dto.ResultDto;
import com.child.programming.base.dto.UploadDto;
import com.child.programming.base.service.IUploadService;
import com.child.programming.base.util.ConstDataUtil;
import com.child.programming.base.util.DateUtil;
import com.child.programming.base.util.EmptyUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zdp
 * @description: TODO
 */
@Service
@Log4j2
public class UploadServiceImpl implements IUploadService {
    /**
     * 允许文件格式
     */
    @Value("${IMAGE.ALLOWSUFFIX}")
    private String allowSuffix;
    /**
     * 允许文件大小10MB
     */
    @Value("${IMAGE.ALLOWSIZE}")
    private String  allowSize;

    private Long getAlloSize(){
       return Long.parseLong(allowSize) * 1024 * 1024;
    }


    /**
     * 命名服务器端新文件
     */
    private String getFileNameNew() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    private UploadDto uploadCommonLogicBlock(String businessType, HttpServletRequest request){

        // 初始化设置文件上传路径
        String basePath = request.getSession().getServletContext().getRealPath(ConstDataUtil.FORWARD_SLASH)
                + ConstDataUtil.UPLOAD_FILES + ConstDataUtil.FORWARD_SLASH;
        // 格式化的年月日
        String nowDateTimeDir = DateUtil.DateToString(new Date(), "yyyy-MM-dd");
        String relativePath = businessType + ConstDataUtil.FORWARD_SLASH ;

        // 根据年月日和taskId创建文件夹
        String floderPath = basePath + relativePath + nowDateTimeDir + ConstDataUtil.FORWARD_SLASH;

        relativePath+= nowDateTimeDir + ConstDataUtil.FORWARD_SLASH;
        return  new UploadDto(relativePath,floderPath);

    }
    @Override
    public List<ResultDto> uploadFiles(String businessType, MultipartFile[] files, HttpServletRequest request) {
        // 初始化设置文件上传路径
         List<ResultDto> resultDtos=null;
        try {
            for (MultipartFile file : files) {

                ResultDto resultDto = uploadFile(businessType, file, request);
                resultDtos.add(resultDto);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return resultDtos;
    }

    @Override
    public ResultDto uploadFile(String businessType, MultipartFile file, HttpServletRequest request) {
         UploadDto uploadDto=  uploadCommonLogicBlock(businessType,request);
         //新名称+Suffix
         String fileNameNew="";
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            int length = allowSuffix.indexOf(suffix);
            if (length == -1) {

                log.error("请上传允许格式的文件！");
                return ResultDto.fail("请上传允许格式的文件！"+"允许上传"+allowSuffix);
            }
            if (file.getSize() > getAlloSize()) {
                log.error("上传的文件大小超出系统允许范围！"+"允许的大小为:0-"+allowSize);
                return ResultDto.fail("上传的文件大小超出系统允许范围！"+"允许的大小为:0-"+allowSize);
            }
            File destFile = new File(uploadDto.getFolderPath());
            if (!destFile.exists()) {
                destFile.mkdirs();
            }

             fileNameNew = getFileNameNew() + "." + suffix;
            File f = new File(destFile.getAbsoluteFile() + ConstDataUtil.FORWARD_SLASH + fileNameNew);
            file.transferTo(f);
            f.createNewFile();

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return  ResultDto.success(uploadDto.getRelativePath() +  fileNameNew);
    }

    @Override
    public String[] uploadFileReturnOriginNameAndFilePath(String businessType, MultipartFile file, HttpServletRequest request) {
        UploadDto uploadDto=  uploadCommonLogicBlock(businessType,request);
        //新名称+Suffix
        String fileNameNew="";

        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            File destFile = new File(uploadDto.getFolderPath());
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            fileNameNew = getFileNameNew() + "." + suffix;
            File f = new File(destFile.getAbsoluteFile() + ConstDataUtil.FORWARD_SLASH + fileNameNew);
            file.transferTo(f);
            f.createNewFile();
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        String[] str={file.getOriginalFilename(),uploadDto.getRelativePath()+fileNameNew};
        return  str;
    }

    @Override
    public String uploadScratch(String businessType, MultipartFile file, HttpServletRequest request) {
        UploadDto uploadDto=  uploadCommonLogicBlock(businessType,request);
        //新名称+Suffix
        String fileNameNew="";

        try {

            File destFile = new File(uploadDto.getFolderPath());
            if (!destFile.exists()) {
                destFile.mkdirs();
            }

             fileNameNew = getFileNameNew() + ".sb3" ;
            File f = new File(destFile.getAbsoluteFile() + ConstDataUtil.FORWARD_SLASH + fileNameNew);
            file.transferTo(f);
            f.createNewFile();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return uploadDto.getRelativePath()+fileNameNew;
    }
}
