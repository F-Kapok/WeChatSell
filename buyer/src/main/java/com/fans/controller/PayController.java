package com.fans.controller;

import com.fans.common.ResponseCode;
import com.fans.dto.OrderDto;
import com.fans.exception.SellException;
import com.fans.service.interfaces.IOrderService;
import com.fans.service.interfaces.IPayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

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
    @Resource(name = "iPayService")
    private IPayService payService;

    @GetMapping(value = "/create")
    public ModelAndView create(@RequestParam(value = "orderId") String orderId,
                               @RequestParam(value = "returnUrl") String returnUrl,
                               Map<String, Object> map) {

        //查询订单
        OrderDto orderDto = orderService.getOne(orderId);
        if (orderDto == null) {
            throw new SellException(ResponseCode.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderDto);
        map.put("response", payResponse);
        map.put("returnUrl", returnUrl);
        return new ModelAndView("pay/create", map);
    }

    @PostMapping(value = "/notify")
    public ModelAndView weChatNotify(@RequestBody String notifyData) {
        payService.weChatNotify(notifyData);
        return new ModelAndView("/pay/success");
    }
}
