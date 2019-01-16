package com.fans.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
public class ProductInfoVO implements Serializable {
    private static final long serialVersionUID = 5948168032901370526L;

    /**
     * 商品主键
     */
    @JsonProperty("id")
    private String productId;

    /**
     * 商品名称
     */
    @JsonProperty("name")
    private String productName;

    /**
     * 单价
     */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**
     * 描述
     */
    @JsonProperty("description")
    private String productDescription;

    /**
     * 小图
     */
    @JsonProperty("icon")
    private String productIcon;

}
