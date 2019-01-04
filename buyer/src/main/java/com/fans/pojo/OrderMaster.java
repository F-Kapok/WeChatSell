package com.fans.pojo;

import com.fans.common.OrderStatusEnum;
import com.fans.common.PayStatusEnum;
import com.fans.uitls.serializer.Date2LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order_master")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class OrderMaster {
    /**
     *
     */
    @Id
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为新下单
     */
    private Byte orderStatus = OrderStatusEnum.NEW.getCode().byteValue();

    /**
     * 支付状态, 默认未支付
     */
    private Byte payStatus = PayStatusEnum.WAIT.getCode().byteValue();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    @JsonSerialize(using = com.fans.uitls.serializer.Date2LongSerializer.class)
    private Date createTime;
    /**
     * 修改时间
     */
    @org.hibernate.annotations.UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = com.fans.uitls.serializer.Date2LongSerializer.class)
    private Date updateTime;

}