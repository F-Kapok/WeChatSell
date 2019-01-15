package com.fans.exception;

/**
 * @ClassName SellerAuthorizeException
 * @Description:
 * @Author fan
 * @Date 2019-01-15 21:17
 * @Version 1.0
 **/
public class SellerAuthorizeException extends RuntimeException {
    public SellerAuthorizeException() {
        super();
    }

    public SellerAuthorizeException(String message) {
        super(message);
    }

    public SellerAuthorizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SellerAuthorizeException(Throwable cause) {
        super(cause);
    }

    protected SellerAuthorizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
