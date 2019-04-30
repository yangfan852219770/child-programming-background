package com.child.programming.base.dto;

import lombok.Data;

/**
 * @author zdp
 * @description: TODO
 */
@Data
public class MaterialInfoDto {

    private Integer id; //自增主键

    private Integer materialTypeId; //资料类别ID

    private String type; //资料类别

    private String introduction; //资料介绍

    private String fileUrl; //资料URL

    private Integer downloadNumber; //下载次数

    private String originName; //资料名称

    private Byte status; //状态

}
