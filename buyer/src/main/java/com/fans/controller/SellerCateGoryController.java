package com.fans.controller;

import com.fans.common.JsonData;
import com.fans.param.CategoryParam;
import com.fans.pojo.ProductCategory;
import com.fans.service.interfaces.IProductCategoryService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName SellerCateGoryController
 * @Description:
 * @Author fan
 * @Date 2019-01-15 12:26
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/seller/category")
@Slf4j
public class SellerCateGoryController {
    @Resource(name = "iProductCategoryService")
    private IProductCategoryService productCategoryService;

    @GetMapping(value = "/list")
    public ModelAndView list() {
        List<ProductCategory> categoryList = productCategoryService.findAll();
        return new ModelAndView("/category/list", JsonData.success(categoryList).toMap());
    }

    @GetMapping(value = "/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId) {
        Map<String, Object> result = Maps.newHashMap();
        if (categoryId != null) {
            ProductCategory productCategory = productCategoryService.selectById(categoryId);
            result = JsonData.success(productCategory).toMap();
            return new ModelAndView("/category/index", result);
        }
        return new ModelAndView("/category/index", result);
    }

    @PostMapping(value = "/save")
    public ModelAndView save(CategoryParam param, BindingResult bindingResult) {
        Map<String, Object> map;
        if (bindingResult.hasErrors()) {
            String errorMsg = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
            log.error("【修改或新增商品类目】发生异常{}", errorMsg);
            map = JsonData.fail(errorMsg).toMap();
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("/common/error", map);
        }
        try {
            ProductCategory category = new ProductCategory();
            if (param.getCategoryId() != null) {
                category = productCategoryService.selectById(param.getCategoryId());
            }
            BeanUtils.copyProperties(param, category);
            productCategoryService.save(category);
        } catch (Exception e) {
            log.error("【修改或新增商品类目】发生异常{}", e);
            map = JsonData.fail(e.getMessage()).toMap();
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("/common/error", map);
        }
        map = JsonData.success().toMap();
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("/common/success", map);
    }
}
