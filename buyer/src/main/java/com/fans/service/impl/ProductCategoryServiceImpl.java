package com.fans.service.impl;

import com.fans.pojo.ProductCategory;
import com.fans.repository.ProductCategoryRepository;
import com.fans.service.interfaces.IProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName ProductCategoryServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-01-02 15:51
 * @Version 1.0
 **/
@Service(value = "iProductCategoryService")
public class ProductCategoryServiceImpl implements IProductCategoryService {
    @Resource(type = com.fans.repository.ProductCategoryRepository.class)
    private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory selectById(Integer categoryId) {
        return categoryRepository.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryRepository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return categoryRepository.save(productCategory);
    }
}
