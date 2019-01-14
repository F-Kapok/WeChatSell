package com.fans.controller;

import com.fans.common.JsonData;
import com.fans.exception.SellException;
import com.fans.pojo.ProductInfo;
import com.fans.service.interfaces.IProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName SellerOrderController
 * @Description:
 * @Author fan
 * @Date 2019-01-11 14:20
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/seller/product")
@Slf4j
public class SellerProductController {
    @Resource(name = "iProductInfoService")
    private IProductInfoService iProductInfoService;

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = iProductInfoService.findAll(pageRequest);
        Map<String, Object> map = JsonData.success(productInfoPage).toMap();
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/product/list", map);
    }

    @GetMapping(value = "/on_sale")
    public ModelAndView onSale(String productId) {
        Map<String, Object> map;
        try {
            iProductInfoService.onSale(productId);
        } catch (SellException e) {
            log.error("【卖家端上架产品】发生异常{}", e);
            map = JsonData.fail(e.getMessage()).toMap();
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }
        map = JsonData.success().toMap();
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }

    @GetMapping(value = "/off_sale")
    public ModelAndView offSale(String productId) {
        Map<String, Object> map;
        try {
            iProductInfoService.offSale(productId);
        } catch (SellException e) {
            log.error("【卖家端下架产品】发生异常{}", e);
            map = JsonData.fail(e.getMessage()).toMap();
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }
        map = JsonData.success().toMap();
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }

}
