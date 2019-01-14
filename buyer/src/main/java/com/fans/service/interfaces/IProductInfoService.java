package com.fans.service.interfaces;

import com.fans.dto.CartDto;
import com.fans.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @InterfaceName IProductInfoService
 * @Description:
 * @Author fan
 * @Date 2019-01-02 17:08
 * @Version 1.0
 **/
public interface IProductInfoService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品列表
     *
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 储存商品
     *
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     *
     * @param cartDTOList
     */
    void increaseStock(List<CartDto> cartDTOList);

    /**
     * 减库存
     *
     * @param cartDTOList
     */
    void decreaseStock(List<CartDto> cartDTOList);

    /**
     * 商品上架
     *
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 商品下架
     *
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);

}
