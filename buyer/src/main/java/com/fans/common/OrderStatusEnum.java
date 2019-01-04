package com.fans.common;

import lombok.Getter;

/**
 * @EnumName OrderStatusEnum
 * @Description:
 * @Author fan
 * @Date 2019-01-03 13:39
 * @Version 1.0
 **/
@Getter
public enum OrderStatusEnum {
    /**
     * 新订单
     */
    NEW(0, "新订单"),
    /**
     * 完结
     */
    FINISHED(1, "完结"),
    /**
     * 已取消
     */
    CANCEL(2, "已取消"),

    ;
    private Integer code;
    private String desc;

    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
