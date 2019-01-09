package com.fans.service.interfaces;

import com.fans.dto.OrderDto;

/**
 * @InterfaceName IPayService
 * @Description:
 * @Author fan
 * @Date 2019-01-09 22:13
 * @Version 1.0
 **/
public interface IPayService {
    void create(OrderDto orderDto);
}
