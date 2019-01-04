package com.fans.service.impl;

import com.fans.common.ProductStatusEnum;
import com.fans.common.ResponseCode;
import com.fans.dto.CartDto;
import com.fans.exception.SellException;
import com.fans.pojo.ProductInfo;
import com.fans.repository.ProductInfoRepository;
import com.fans.service.interfaces.IProductInfoService;
import com.fans.uitls.CommonUtil;
import com.google.common.base.Preconditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName ProductInfoServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-01-02 17:10
 * @Version 1.0
 **/
@Service(value = "iProductInfoService")
public class ProductInfoServiceImpl implements IProductInfoService {
    @Resource(type = com.fans.repository.ProductInfoRepository.class)
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {

        return productInfoRepository.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode().byteValue());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void increaseStock(List<CartDto> cartDTOList) {
        for (CartDto cart : cartDTOList) {
            Optional<ProductInfo> optional = productInfoRepository.findById(cart.getProductId());
            ProductInfo productInfo = CommonUtil.checkObjectExist(optional, ResponseCode.PRODUCT_NOT_EXIST);
            int result = productInfo.getProductStock() + cart.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void decreaseStock(List<CartDto> cartDTOList) {
        for (CartDto cart : cartDTOList) {
            Optional<ProductInfo> optional = productInfoRepository.findById(cart.getProductId());
            ProductInfo productInfo = CommonUtil.checkObjectExist(optional, ResponseCode.PRODUCT_NOT_EXIST);
            int result = productInfo.getProductStock() - cart.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResponseCode.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
