package com.fans.controller;

import com.fans.common.JsonData;
import com.fans.common.ResponseCode;
import com.fans.dto.OrderDto;
import com.fans.exception.SellException;
import com.fans.service.interfaces.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName SellerOrderController
 * @Description:
 * @Author fan
 * @Date 2019-01-11 14:20
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/seller/order")
@Slf4j
public class SellerOrderController {
    @Resource(name = "iOrderService")
    private IOrderService orderService;

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<OrderDto> orderDtoPage = orderService.findList(pageRequest);
        Map<String, Object> map = JsonData.success(orderDtoPage).toMap();
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("/order/list", map);
    }

    @GetMapping(value = "/cancel")
    public ModelAndView cancel(@RequestParam(value = "orderId") String orderId) {
        Map<String, Object> map;
        try {
            OrderDto orderDto = orderService.getOne(orderId);
            orderService.cancel(orderDto);
        } catch (SellException e) {
            log.error("【卖家端取消订单】发生异常{}", e);
            map = JsonData.fail(e.getMessage()).toMap();
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("/common/error", map);
        }
        map = JsonData.success(ResponseCode.ORDER_CANCEL_SUCCESS.getDesc()).toMap();
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("/common/success", map);
    }
}
