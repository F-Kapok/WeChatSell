package com.fans.handler;

import com.fans.common.JsonData;
import com.fans.config.ProductConstant;
import com.fans.exception.SellException;
import com.fans.exception.SellerAuthorizeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @ClassName SellerExceptionHandler
 * @Description:
 * @Author fan
 * @Date 2019-01-15 21:20
 * @Version 1.0
 **/
@ControllerAdvice
public class SellerExceptionHandler {
    @Resource(name = "productConstant")
    private ProductConstant productConstant;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(productConstant.getUrl())
                .concat("/sell/seller/index"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public JsonData handlerSellException(SellException e) {
        return JsonData.failCodeMsg(e.getCode(), e.getMessage());
    }
}
