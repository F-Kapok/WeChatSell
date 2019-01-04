package com.fans.service.impl;

import com.fans.pojo.ProductCategory;
import com.fans.service.interfaces.IProductCategoryService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Resource(name = "iProductCategoryService")
    private IProductCategoryService productCategoryService;

    @Test
    public void selectById() {
        ProductCategory productCategory = productCategoryService.selectById(1);
        assertNotEquals(null, productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = productCategoryService.findAll();
        assertNotEquals(0, all.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Lists.newArrayList(1, 2);
        List<ProductCategory> byCategoryTypeIn = productCategoryService.findByCategoryTypeIn(list);
        assertNotEquals(0, byCategoryTypeIn.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = ProductCategory.builder()
                .categoryName("男生最爱")
                .categoryType(3)
                .build();
        ProductCategory save = productCategoryService.save(productCategory);
        assertNotEquals(null, save);
    }
}