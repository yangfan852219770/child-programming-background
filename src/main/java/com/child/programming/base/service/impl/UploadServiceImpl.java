package com.child.programming.base.service.impl;

import com.child.programming.base.service.IUploadService;
import com.child.programming.base.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
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

    /**
     * 文件上传路径
     */
    private String serverUploadFilePath;


    /**
     * 命名服务器端新文件
     */
    private String getFileNameNew() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    @Override
    public String[] uploadFiles(String businessType, MultipartFile[] files, HttpServletRequest request) {
        // 初始化设置文件上传路径
        serverUploadFilePath = request.getSession().getServletContext().getRealPath("/");
         String[] filePaths=null;
        try {
            filePaths = new String[files.length];
            int index = 0;
            for (MultipartFile file : files) {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                int length = allowSuffix.indexOf(suffix);
                if (length == -1) {
                    log.error("请上传允许格式的文件！");
                }
                if (file.getSize() > Long.parseLong(allowSize)) {
                    log.error("上传的文件大小超出系统允许范围！");
                }

                File destFile = new File(serverUploadFilePath);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                String fileNameNew = getFileNameNew() + "." + suffix;//
                File f = new File(destFile.getAbsoluteFile() + "\\" + fileNameNew);
                file.transferTo(f);
                f.createNewFile();
                filePaths[index++] = serverUploadFilePath + fileNameNew;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return filePaths;
    }

    @Override
    public String uploadFile(String businessType, MultipartFile file, HttpServletRequest request) {
        System.out.println(businessType);
        // 初始化设置文件上传路径
        serverUploadFilePath = request.getSession().getServletContext().getRealPath("/") + "upload_files/"
                + businessType + "/";

        String filePath = "";
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            int length = allowSuffix.indexOf(suffix);
            if (length == -1) {
                log.error("请上传允许格式的文件！");
            }
            if (file.getSize() > Long.parseLong(allowSize)) {
                log.error("上传的文件大小超出系统允许范围！");
            }

            // 格式化的年月日
            String nowDateTimeDir = DateUtil.DateToString(new Date(), "yyyy-MM-dd");
            // 根据年月日和taskId创建文件夹
            String path = serverUploadFilePath + nowDateTimeDir + "/";

            File destFile = new File(path);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            String fileNameNewStr = getFileNameNew();
            String fileNameNew = fileNameNewStr + "." + suffix;
            File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
            file.transferTo(f);
            f.createNewFile();

            filePath = path + fileNameNew;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        System.out.println(filePath);
        return filePath;
    }
}
