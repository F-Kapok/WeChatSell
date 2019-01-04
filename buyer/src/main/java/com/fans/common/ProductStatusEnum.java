package com.fans.common;

import lombok.Getter;

/**
 * @EnumName ProductStatusEnum
 * @Description:
 * @Author fan
 * @Date 2019-01-02 17:04
 * @Version 1.0
 **/
@Getter
public enum ProductStatusEnum {
    /**
     * 上架
     */
    UP(0, "上架"),
    /**
     * 下架
     */
    DOWN(1, "下架");

    private Integer code;
    private String desc;

    ProductStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
