package com.fans.uitls;

import com.fans.common.CodeEnum;

/**
 * @ClassName EnumUtil
 * @Description:
 * @Author fan
 * @Date 2019-01-11 15:13
 * @Version 1.0
 **/
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
