package com.fans.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName WinXinController
 * @Description: 微信控制层
 * @Author fan
 * @Date 2019-01-04 15:31
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/weixin")
@Slf4j
public class WeiXinController {

    @GetMapping(value = "/auth")
    public void auth(@RequestParam(value = "code") String code) {
        log.info("-->进入Auth方法。。。");
        log.info("-->" + code);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=wx6d790fe13fcf7a82" +
                "&secret=8fa58d6612648f4f204362155bdb5210" +
                "&code=" + code + "" +
                "&grant_type=authorization_code";
        String result = restTemplate.getForObject(url, String.class);
        log.info("-->" + result);
    }
}
