package com.fans.controller;

import com.fans.common.ResponseCode;
import com.fans.dto.OrderDto;
import com.fans.exception.SellException;
import com.fans.service.interfaces.IOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @ClassName PayController
 * @Description:
 * @Author fan
 * @Date 2019-01-06 14:53
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/pay")
public class PayController {
    @Resource(name = "iOrderService")
    private IOrderService orderService;

    @GetMapping(value = "/create")
    public void create(@RequestParam(value = "orderId") String orderId,
                       @RequestParam(value = "returnUrl") String returnUrl) {

        //查询订单
        OrderDto orderDto = orderService.getOne(orderId);
        if (orderDto == null) {
            throw new SellException(ResponseCode.ORDER_NOT_EXIST);
        }
        //发起支付
    }
}
