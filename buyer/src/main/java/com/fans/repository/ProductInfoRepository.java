package com.fans.repository;

import com.fans.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @InterfaceName ProductInfoRepository
 * @Description:
 * @Author fan
 * @Date 2019-01-02 17:02
 * @Version 1.0
 **/
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    List<ProductInfo> findByProductStatus(Byte productStatus);
}
