package com.child.programming.base.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zdp
 * @description: TODO
 */
@Data
public class UploadDto {

    private String relativePath;

    private String folderPath;

    private String originName;

    private String dbRelativePath;


    public UploadDto(String relativePath,String folderPath){
        this.folderPath=folderPath;
        this.relativePath=relativePath;
    }

}
