package com.fans.controller;

import com.fans.config.ProductConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @ClassName SellerPage
 * @Description: 卖家管理平台首页
 * @Author fan
 * @Date 2019-02-22 18:04
 * @Version 1.0
 **/
@RestController
public class SellerPage {
    @Resource(name = "productConstant")
    private ProductConstant productConstant;

    @GetMapping(value = "/admin")
    public ModelAndView index() {
        return new ModelAndView("redirect:"
                .concat(productConstant.getUrl())
                .concat("/sell/seller/order/list"));
    }
}
