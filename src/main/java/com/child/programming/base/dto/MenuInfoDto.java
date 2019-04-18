package com.child.programming.base.dto;

import lombok.Data;

import java.util.List;

/**
 * @author zdp
 * @description: TODO
 */
@Data
public class MenuInfoDto {

    private Integer id;

    private String path;

    private String name;

    private String icon;

    private String locale;

    private Boolean exact;

    private String[] authority;

    private List<?> children;

}
