package com.fans.repository;

import com.fans.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ProductCategoryRepository
 * @Description:
 * @Author fan
 * @Date 2019-01-02 15:43
 * @Version 1.0
 **/
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 根据产品类型查找类目
     *
     * @param categoryType
     * @return: java.util.List<com.fans.pojo.ProductCategory>
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);

}
