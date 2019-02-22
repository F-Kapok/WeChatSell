package com.fans.vo;

import com.fans.pojo.ProductInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName ProductInfoVO
 * @Description:
 * @Author fan
 * @Date 2018-12-26 11:22
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "商品信息展示对象")
public class ProductInfoVO implements Serializable {
    private static final long serialVersionUID = 5948168032901370526L;

    /**
     * 商品主键
     */
    @JsonProperty("id")
    @ApiModelProperty(value = "商品ID")
    private String productId;

    /**
     * 商品名称
     */
    @JsonProperty("name")
    @ApiModelProperty(value = "商品名称")
    private String productName;

    /**
     * 单价
     */
    @JsonProperty("price")
    @ApiModelProperty(value = "商品单价")
    private BigDecimal productPrice;

    /**
     * 描述
     */
    @JsonProperty("description")
    @ApiModelProperty(value = "商品描述")
    private String productDescription;

    /**
     * 小图
     */
    @JsonProperty("icon")
    @ApiModelProperty(value = "商品图片")
    private String productIcon;

    public static ProductInfoVO adapt(ProductInfo productInfo) {
        ProductInfoVO productInfoVO = ProductInfoVO.builder().build();
        BeanUtils.copyProperties(productInfo, productInfoVO);
        return productInfoVO;
    }

}
