package com.fans.dto;

import com.fans.common.OrderStatusEnum;
import com.fans.common.PayStatusEnum;
import com.fans.pojo.OrderDetail;
import com.fans.pojo.OrderMaster;
import com.fans.uitls.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @ClassName OrderDto
 * @Description:
 * @Author fan
 * @Date 2019-01-03 14:13
 * @Version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDto extends OrderMaster {
    private List<OrderDetail> orderDetailList = Lists.newArrayList();

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(super.getOrderStatus().intValue(), OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(super.getPayStatus().intValue(), PayStatusEnum.class);
    }

    public static OrderDto adapt(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }
}
