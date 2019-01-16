package com.fans.service.interfaces;

import com.fans.dto.OrderDto;

/**
 * @InterfaceName IPushMessageService
 * @Description: 推送消息
 * @Author fan
 * @Date 2019-01-16 09:44
 * @Version 1.0
 **/
public interface IPushMessageService {

    void orderStatusPush(OrderDto orderDto);

}
