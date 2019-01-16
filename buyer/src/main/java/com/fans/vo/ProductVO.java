package com.fans.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ProductVO
 * @Description:
 * @Author fan
 * @Date 2018-12-26 09:58
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVO implements Serializable {
    
    private static final long serialVersionUID = 4305639511386554470L;

    @JsonProperty("name")
    private String cateGoryName;

    @JsonProperty("type")
    private Integer cateGoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
