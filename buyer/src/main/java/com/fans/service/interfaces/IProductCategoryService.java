package com.fans.service.interfaces;

import com.fans.pojo.ProductCategory;

import java.util.List;

/**
 * @InterfaceName IProductCategoryService
 * @Description:
 * @Author fan
 * @Date 2019-01-02 15:51
 * @Version 1.0
 **/
public interface IProductCategoryService {
    ProductCategory selectById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
