package com.fans.dto;

import lombok.*;

/**
 * @ClassName CartDto
 * @Description:
 * @Author fan
 * @Date 2019-01-03 15:11
 * @Version 1.0
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartDto {
    /**
     * 商品Id.
     */
    private String productId;

    /**
     * 数量.
     */
    private Integer productQuantity;

}
