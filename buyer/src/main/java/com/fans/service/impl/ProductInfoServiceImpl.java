package com.fans.service.impl;

import com.fans.common.ProductStatusEnum;
import com.fans.common.ResponseCode;
import com.fans.dto.CartDto;
import com.fans.exception.SellException;
import com.fans.pojo.ProductInfo;
import com.fans.repository.ProductInfoRepository;
import com.fans.service.interfaces.IProductInfoService;
import com.fans.uitls.CommonUtil;
import com.fans.vo.ProductInfoVO;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
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
        Optional<ProductInfo> byId = productInfoRepository.findById(productId);
        return byId.orElse(null);
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

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResponseCode.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResponseCode.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode().byteValue());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = findOne(productId);
        if (productInfo == null) {
            throw new SellException(ResponseCode.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResponseCode.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode().byteValue());
        return productInfoRepository.save(productInfo);
    }

    @Override
    public List<ProductInfo> searchProDuctUp(String proName) {
        List<ProductInfo> productInfoList = Lists.newArrayList();
        if (StringUtils.isNotBlank(proName)) {
            productInfoList = productInfoRepository.findByProductNameAndProductStatus(proName, ProductStatusEnum.UP.getCode().byteValue());
        } else {
            productInfoList = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode().byteValue());
        }
        return productInfoList;
    }
}
