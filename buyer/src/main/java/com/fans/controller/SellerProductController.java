package com.fans.controller;

import com.fans.common.JsonData;
import com.fans.exception.SellException;
import com.fans.param.ProductParam;
import com.fans.pojo.ProductCategory;
import com.fans.pojo.ProductInfo;
import com.fans.service.interfaces.IProductCategoryService;
import com.fans.service.interfaces.IProductInfoService;
import com.fans.uitls.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
@CacheConfig(cacheNames = "product")
public class SellerProductController {
    @Resource(name = "iProductInfoService")
    private IProductInfoService productInfoService;
    @Resource(name = "iProductCategoryService")
    private IProductCategoryService productCategoryService;

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        Map<String, Object> map = JsonData.success(productInfoPage).toMap();
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/product/list", map);
    }

    @GetMapping(value = "/on_sale")
    public ModelAndView onSale(String productId) {
        Map<String, Object> map;
        try {
            productInfoService.onSale(productId);
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
            productInfoService.offSale(productId);
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


    @GetMapping(value = "/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId) {
        Map<String, Object> result = JsonData.success().toMap();
        if (StringUtils.isNotBlank(productId)) {
            ProductInfo productInfo = productInfoService.findOne(productId);
            result.put("productInfo", productInfo);
        }
        List<ProductCategory> categoryList = productCategoryService.findAll();
        result.put("categoryList", categoryList);

        return new ModelAndView("/product/index", result);
    }

    @PostMapping(value = "/save")
    @CacheEvict(key = "123", condition = "#result.getModel().get('code')==0")
    public ModelAndView save(@Valid ProductParam param, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String defaultMessage = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            log.error("【卖家端新增或更新产品】发生异常{}", defaultMessage);
            Map<String, Object> map = JsonData.fail(defaultMessage).toMap();
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }
        try {
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductStock(param.getProductStock());
            if (StringUtils.isNotBlank(param.getProductId())) {
                productInfo = productInfoService.findOne(param.getProductId());
            } else {
                param.setProductId(IdUtil.getTimestampId());
            }
            BeanUtils.copyProperties(param, productInfo);
            productInfoService.save(productInfo);
        } catch (Exception e) {
            log.error("【卖家端新增或更新产品】发生异常{}", e);
            Map<String, Object> map = JsonData.fail(e.getMessage()).toMap();
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }
        Map<String, Object> map = JsonData.success().toMap();
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }
}
