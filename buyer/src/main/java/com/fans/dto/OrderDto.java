package com.fans.dto;

import com.fans.pojo.OrderDetail;
import com.fans.pojo.OrderMaster;
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

    public static OrderDto adapt(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }
}
