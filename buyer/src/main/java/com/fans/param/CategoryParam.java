package com.fans.param;

import lombok.Data;

/**
 * @ClassName CategoryParam
 * @Description:
 * @Author fan
 * @Date 2019-01-15 12:55
 * @Version 1.0
 **/
@Data
public class CategoryParam {

    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;
}
