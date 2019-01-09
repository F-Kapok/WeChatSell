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

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;
}
