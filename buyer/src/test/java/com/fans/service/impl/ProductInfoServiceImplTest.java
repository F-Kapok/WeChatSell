package com.fans.service.impl;

import com.fans.common.ProductStatusEnum;
import com.fans.pojo.ProductInfo;
import com.fans.service.interfaces.IProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {
    @Resource(name = "iProductInfoService")
    private IProductInfoService productInfoService;

    @Test
    public void findOne() {
        ProductInfo one = productInfoService.findOne("157875196366160022");
        assertNotEquals(null, one);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productInfoService.findUpAll();
        assertNotEquals(0, upAll.size());
    }

    @Test
    public void findAll() {
        PageRequest page = PageRequest.of(0, 2);
        Page<ProductInfo> all = productInfoService.findAll(page);
        assertNotEquals(null, all);
    }

    @Test
    public void save() {
        ProductInfo productInfo = ProductInfo.builder()
                .productId(String.valueOf(System.currentTimeMillis()))
                .productName("烤翅")
                .productPrice(new BigDecimal(11.5))
                .productStock(500)
                .productDescription("香嫩的烤鸡翅")
                .productIcon("//fuss10.elemecdn.com/7/4a/f307f56216b03f067155aec8b124ejpeg.jpeg")
                .productStatus(ProductStatusEnum.UP.getCode().byteValue())
                .categoryType(1)
                .build();
        ProductInfo save = productInfoService.save(productInfo);
        assertNotEquals(null, save);
    }
}