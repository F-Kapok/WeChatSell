package com.fans.controller;

import com.fans.common.JsonData;
import com.fans.common.ResponseCode;
import com.fans.config.ProductConstant;
import com.fans.param.LoginParam;
import com.fans.service.interfaces.IUserInfoService;
import com.fans.uitls.CookieUtils;
import com.google.common.base.Utf8;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SellerUserController
 * @Description: 卖家端用户控制层
 * @Author fan
 * @Date 2019-01-15 15:21
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/seller")
public class SellerUserController {
    @Resource(name = "iUserInfoService")
    private IUserInfoService userInfoService;
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;
    @Resource(name = "productConstant")
    private ProductConstant productConstant;

    @GetMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView("/user/login", Maps.newHashMap());
    }

    @PostMapping(value = "/login")
    public ModelAndView login(@Valid LoginParam param,
                              BindingResult bindingResult,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        Map<String, Object> result;
        String uuid = UUID.randomUUID().toString();
        if (bindingResult.hasErrors()) {
            result = JsonData.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage()).toMap();
            result.put("url", "/sell/seller/index");
            return new ModelAndView("/common/error", result);
        }
        boolean ok = userInfoService.findByUsernameAndPassword(param.getUsername(), param.getPassword());
        if (!ok) {
            result = JsonData.fail(ResponseCode.LOGIN_FAIL.getDesc()).toMap();
            result.put("url", "/sell/seller/index");
            return new ModelAndView("/common/error", result);
        }

        redisTemplate.opsForValue().set(String.format(productConstant.getTOKEN(), uuid), param.getUsername(), productConstant.getTIME_OUT(), TimeUnit.SECONDS);
        CookieUtils.setCookie(request, response, productConstant.getCOOKIE_TOKEN(), uuid, productConstant.getTIME_OUT());
        return new ModelAndView("redirect:" + productConstant.getUrl() + "/sell/seller/order/list");
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response) {
        String cookieValue = CookieUtils.getCookieValue(request, productConstant.getCOOKIE_TOKEN(), StandardCharsets.UTF_8.name());
        if (StringUtils.isNotBlank(cookieValue)) {
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(productConstant.getTOKEN(), cookieValue));
            //清除cookie
            CookieUtils.deleteCookie(request, response, productConstant.getCOOKIE_TOKEN());
        }
        Map<String, Object> result = JsonData.success(ResponseCode.LOGOUT_SUCCESS.getDesc()).toMap();
        result.put("url", "/sell/seller/index");
        return new ModelAndView("/common/success", result);
    }
}
