package com.fans.controller;

import com.fans.common.ResponseCode;
import com.fans.config.ProductConstant;
import com.fans.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName WeChatController
 * @Description:
 * @Author fan
 * @Date 2019-01-05 20:10
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/wechat")
@Slf4j
public class WeChatController {
    @Resource(name = "wxMpService")
    private WxMpService wxMpService;
    @Resource(name = "productConstant")
    private ProductConstant productConstant;

    @GetMapping(value = "/authorize")
    public String authorize(@RequestParam(value = "returnUrl") String returnUrl) throws UnsupportedEncodingException {
        //配置
        //调用方发
        String url = productConstant.getUrl() + "/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl, StandardCharsets.UTF_8.name()));
        return "redirect:" + redirectUrl;
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(@RequestParam(value = "code") String code,
                           @RequestParam(value = "state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信授权登录】 {}", e);
            throw new SellException(ResponseCode.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
