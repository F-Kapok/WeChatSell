package com.fans.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @ClassName WeChatMpConfig
 * @Description:
 * @Author fan
 * @Date 2019-01-05 20:19
 * @Version 1.0
 **/
@Configuration
public class WeChatMpConfiguration {

    @Resource(name = "weChatConstant")
    private WeChatConstant weChatConstant;

    @Bean(name = "wxMpService")
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(weChatConstant.getMpAppId());
        wxMpConfigStorage.setSecret(weChatConstant.getMpAppSecret());
        return wxMpConfigStorage;
    }

}
