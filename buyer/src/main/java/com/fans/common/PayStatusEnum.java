package com.fans.common;

import lombok.Getter;

/**
 * @EnumName PayStatusEnum
 * @Description:
 * @Author fan
 * @Date 2019-01-03 13:41
 * @Version 1.0
 **/
@Getter
public enum PayStatusEnum implements CodeEnum{
    /**
     * 等待支付
     */
    WAIT(0, "等待支付"),
    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功"),

    ;
    private Integer code;
    private String desc;

    PayStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
