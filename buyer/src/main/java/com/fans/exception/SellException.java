package com.fans.exception;

import com.fans.common.ResponseCode;

/**
 * @ClassName SellException
 * @Description:
 * @Author fan
 * @Date 2019-01-03 14:44
 * @Version 1.0
 **/
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResponseCode responseCode) {
        super(responseCode.getDesc());
        this.code = responseCode.getCode();
    }

    public SellException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
    }
}
