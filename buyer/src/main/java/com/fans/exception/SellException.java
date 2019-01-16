package com.fans.exception;

import com.fans.common.ResponseCode;
import lombok.Data;

/**
 * @ClassName SellException
 * @Description:
 * @Author fan
 * @Date 2019-01-03 14:44
 * @Version 1.0
 **/
@Data
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
