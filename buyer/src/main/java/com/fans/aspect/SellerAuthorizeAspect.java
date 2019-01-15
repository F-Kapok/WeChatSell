package com.fans.aspect;

import com.fans.config.ProductConstant;
import com.fans.exception.SellerAuthorizeException;
import com.fans.uitls.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName SellerAuthorizeAspect
 * @Description:
 * @Author fan
 * @Date 2019-01-15 20:51
 * @Version 1.0
 **/
@Aspect
@Component(value = "sellerAuthorizeAspect")
@Slf4j
public class SellerAuthorizeAspect {

    @Resource(name = "productConstant")
    private ProductConstant productConstant;
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Pointcut(value = "execution(public * com.fans.controller.Seller*.*(..)) &&" +
            "!execution(public * com.fans.controller.SellerUserController.*(..))")
    public void verify() {
    }

    @Before(value = "verify()")
    public void doVerify() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String cookieValue = CookieUtils.getCookieValue(request, productConstant.getCOOKIE_TOKEN(), StandardCharsets.UTF_8.name());
        if (StringUtils.isBlank(cookieValue)) {
            log.warn("【登录校验】不通过，Cookie查询不到token");
            throw new SellerAuthorizeException();
        }
        String openId = redisTemplate.opsForValue().get(String.format(productConstant.getTOKEN(), cookieValue));
        if (StringUtils.isBlank(openId)) {
            log.warn("【登录校验】不通过，Redis查询不到openId");
            throw new SellerAuthorizeException();
        }
    }
}
