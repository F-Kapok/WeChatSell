package com.fans.service.impl;

import com.fans.common.ResponseCode;
import com.fans.dto.OrderDto;
import com.fans.exception.SellException;
import com.fans.service.interfaces.IBuyerService;
import com.fans.service.interfaces.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName BuyerServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-01-04 14:07
 * @Version 1.0
 **/
@Service(value = "iBuyerService")
@Slf4j
public class BuyerServiceImpl implements IBuyerService {
    @Resource(name = "iOrderService")
    private IOrderService orderService;

    @Override
    public OrderDto buyerFindOrderDetail(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDto buyerCancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if (orderDto == null) {
            log.error("【取消订单】错误，查不到此订单 orderId = {}", orderId);
            throw new SellException(ResponseCode.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }


    private OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDto = orderService.getOne(orderId);
        if (orderDto == null) {
            return null;
        }
        if (!StringUtils.equals(orderDto.getBuyerOpenid(), openid)) {
            log.error("订单openid不一致，openid = {}，orderDto = {}", openid, orderDto);
            throw new SellException(ResponseCode.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
