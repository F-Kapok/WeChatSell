package com.fans.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName ProductCategory
 * @Author fan
 * @Date 2019-01-02 15:43
 * @Version 1.0
 **/
@Entity
@Table(name = "product_category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ProductCategory {
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目名字
     */
    private String categoryName;

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Date createTime;
    /**
     * 修改时间
     */
    @org.hibernate.annotations.UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

}