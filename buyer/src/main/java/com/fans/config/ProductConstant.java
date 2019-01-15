package com.fans.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProductConstant
 * @Description:
 * @Author fan
 * @Date 2019-01-15 15:16
 * @Version 1.0
 **/
@Component(value = "productConstant")
@ConfigurationProperties(prefix = "producturl")
@Data
public class ProductConstant {
    private String url;

    private String local;

    private final String TOKEN = "token_%s";

    private final String COOKIE_TOKEN = "token";

    private final Integer TIME_OUT = 7200;

}
