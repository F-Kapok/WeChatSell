package com.fans.service.interfaces;

import com.fans.dto.OrderDto;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @InterfaceName IPayService
 * @Description:
 * @Author fan
 * @Date 2019-01-09 22:13
 * @Version 1.0
 **/
public interface IPayService {
    PayResponse create(OrderDto orderDto);

    PayResponse weChatNotify(String notifyData);

    RefundResponse refund(OrderDto orderDto);
}
