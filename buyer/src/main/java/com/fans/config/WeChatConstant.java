package com.fans.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName WeChatConstant
 * @Description:
 * @Author fan
 * @Date 2019-01-05 20:25
 * @Version 1.0
 **/

@Component(value = "weChatConstant")
@ConfigurationProperties(prefix = "wechat")
@Data
public class WeChatConstant {
    private String mpAppId;

    private String mpAppSecret;
}
