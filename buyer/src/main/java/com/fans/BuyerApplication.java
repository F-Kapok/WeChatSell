package com.fans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BuyerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyerApplication.class, args);
    }

}

