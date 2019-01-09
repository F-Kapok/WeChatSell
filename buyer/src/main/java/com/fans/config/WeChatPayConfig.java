package com.fans.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @ClassName WeChatPayConfig
 * @Description:
 * @Author fan
 * @Date 2019-01-09 22:57
 * @Version 1.0
 **/
@Configuration
public class WeChatPayConfig {
    @Resource(name = "weChatConstant")
    private WeChatConstant weChatConstant;

    @Bean(name = "bestPayService")
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(this.wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(weChatConstant.getMpAppId());
        wxPayH5Config.setAppSecret(weChatConstant.getMpAppSecret());
        wxPayH5Config.setMchId(weChatConstant.getMchId());
        wxPayH5Config.setMchKey(weChatConstant.getMchKey());
        wxPayH5Config.setKeyPath(weChatConstant.getKeyPath());
        wxPayH5Config.setNotifyUrl(weChatConstant.getNotifyUrl());
        return wxPayH5Config;
    }
}
