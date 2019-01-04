package com.fans.controller;

import com.fans.common.JsonData;
import com.fans.common.ResponseCode;
import com.fans.dto.OrderDto;
import com.fans.exception.SellException;
import com.fans.param.OrderParam;
import com.fans.pojo.OrderDetail;
import com.fans.service.interfaces.IBuyerService;
import com.fans.service.interfaces.IOrderService;
import com.fans.uitls.JsonMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BuyerOrderController
 * @Description:
 * @Author fan
 * @Date 2019-01-04 10:42
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Resource(name = "iOrderService")
    private IOrderService orderService;
    @Resource(name = "iBuyerService")
    private IBuyerService buyerService;

    /**
     * 创建订单
     */
    @PostMapping(value = "/create")
    public JsonData<Map<String, String>> create(@Valid OrderParam orderParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderParam = {}", orderParam);
            throw new SellException(ResponseCode.PARAM_ERROR.getCode()
                    , bindingResult.getFieldError().getDefaultMessage());
        }
        //对象转换
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderParam.getName());
        orderDto.setBuyerPhone(orderParam.getPhone());
        orderDto.setBuyerAddress(orderParam.getAddress());
        orderDto.setBuyerOpenid(orderParam.getOpenid());
        List<OrderDetail> detailList;
        try {
            detailList = JsonMapper.string2Obj(orderParam.getItems(), new TypeReference<List<OrderDetail>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
        } catch (Exception e) {
            log.error("【对象转换】错误，String = {}", orderParam.getItems());
            throw new SellException(ResponseCode.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(detailList);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【创建订单】错误，购物车不能为空");
            throw new SellException(ResponseCode.CART_EMPTY);
        }
        OrderDto createResult = orderService.create(orderDto);
        Map<String, String> resultMap = Maps.newHashMap();
        resultMap.put("orderId", createResult.getOrderId());
        return JsonData.success("创建订单成功", resultMap);
    }

    /**
     * 订单列表
     */
    @GetMapping(value = "/list")
    public JsonData<List<OrderDto>> list(@RequestParam(value = "openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isBlank(openid)) {
            log.error("【查询订单列表】参数错误");
            throw new SellException(ResponseCode.PARAM_ERROR);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDto> orderDtoList = orderService.findList(openid, pageRequest);
        return JsonData.success("查询订单列表成功", orderDtoList.getContent());
    }

    /**
     * 订单详情
     */
    @GetMapping(value = "/detail")
    public JsonData<OrderDto> orderDetail(@RequestParam(value = "openid") String openid,
                                          @RequestParam(value = "orderId") String orderId) {
        //FIXME 防止横向越权 改进

        OrderDto orderDto = buyerService.buyerFindOrderDetail(openid, orderId);
        return JsonData.success("查询订单详情成功", orderDto);
    }

    /**
     * 取消订单
     */
    @PostMapping(value = "/cancel")
    public JsonData cancel(@RequestParam(value = "openid") String openid,
                           @RequestParam(value = "orderId") String orderId) {
        buyerService.buyerCancelOrder(openid, orderId);
        return JsonData.success("取消订单成功");
    }
}
