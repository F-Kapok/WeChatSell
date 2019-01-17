package com.fans.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @ClassName ProductParam
 * @Description:
 * @Author fan
 * @Date 2019-01-15 10:20
 * @Version 1.0
 **/
@Data
public class ProductParam {

    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 单价
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 类目编号
     */
    private Integer categoryType;
}
