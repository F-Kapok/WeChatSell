package com.fans.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

/**
 * @ClassName JpaLazyConfiguration
 * @Description:
 * @Author fan
 * @Date 2019-01-17 09:41
 * @Version 1.0
 **/
@Configuration
public class JpaLazyConfiguration {

    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
    
}
