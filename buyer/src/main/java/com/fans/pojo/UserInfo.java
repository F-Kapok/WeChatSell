package com.fans.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserInfo {
    /**
     *
     */
    @Id
    private String id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String password;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 0买家1卖家
     */
    private Boolean role;

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