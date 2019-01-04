package com.fans.uitls;

import com.fans.common.ResponseCode;
import com.fans.exception.SellException;

import java.util.Optional;

/**
 * @ClassName CommonUtil
 * @Description:
 * @Author fan
 * @Date 2019-01-03 16:21
 * @Version 1.0
 **/
public class CommonUtil {

    public static <T> T checkObjectExist(Optional<T> optional, ResponseCode responseCode) {
        if (!optional.isPresent()) {
            throw new SellException(responseCode);
        }
        return optional.get();
    }
}
