package com.fans.service.impl;

import com.fans.common.OrderStatusEnum;
import com.fans.common.PayStatusEnum;
import com.fans.common.ResponseCode;
import com.fans.dto.CartDto;
import com.fans.dto.OrderDto;
import com.fans.exception.SellException;
import com.fans.pojo.OrderDetail;
import com.fans.pojo.OrderMaster;
import com.fans.pojo.ProductInfo;
import com.fans.repository.OrderDetailRepository;
import com.fans.repository.OrderMasterRepository;
import com.fans.repository.ProductInfoRepository;
import com.fans.service.interfaces.IOrderService;
import com.fans.service.interfaces.IProductInfoService;
import com.fans.uitls.CommonUtil;
import com.fans.uitls.IdUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName OrderServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-01-03 14:24
 * @Version 1.0
 **/
@Service(value = "iOrderService")
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Resource(type = com.fans.repository.ProductInfoRepository.class)
    private ProductInfoRepository productInfoRepository;

    @Resource(type = com.fans.repository.OrderMasterRepository.class)
    private OrderMasterRepository masterRepository;

    @Resource(type = com.fans.repository.OrderDetailRepository.class)
    private OrderDetailRepository detailRepository;

    @Resource(name = "iProductInfoService")
    private IProductInfoService productInfoService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDto create(OrderDto orderDto) {
        String orderId = String.valueOf(IdUtil.genItemId());
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        //1、查询商品（数量，价格）
        for (OrderDetail detail : orderDto.getOrderDetailList()) {
            Optional<ProductInfo> optional = productInfoRepository.findById(detail.getProductId());
            ProductInfo productInfo = CommonUtil.checkObjectExist(optional, ResponseCode.PRODUCT_NOT_EXIST);
            //2、计算总价
            amount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(detail.getProductQuantity()))
                    .add(amount);
            //3、订单详情入库
            detail.setDetailId(String.valueOf(IdUtil.genItemId()));
            detail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, detail);
            detailRepository.save(detail);
        }
        //4、写入订单数据库（master 和 detail）
        OrderMaster orderMaster = OrderMaster.builder().build();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(amount);
        masterRepository.save(orderMaster);

        //5、扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(orderDetail -> CartDto.builder()
                .productId(orderDetail.getProductId())
                .productQuantity(orderDetail.getProductQuantity())
                .build()).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto getOne(String orderId) {
        Optional<OrderMaster> optional = masterRepository.findById(orderId);
        OrderMaster orderMaster = CommonUtil.checkObjectExist(optional, ResponseCode.ORDER_NOT_EXIST);
        List<OrderDetail> orderDetailList = detailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResponseCode.ORDER_DETAIL_EMPTY);
        }
        OrderDto orderDto = OrderDto.adapt(orderMaster);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = masterRepository.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDto> orderDtoList = orderMasterPage.stream()
                .map(OrderDto::adapt).collect(Collectors.toList());
        return new PageImpl<>(orderDtoList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode().byteValue())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResponseCode.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode().byteValue());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster saveResult = masterRepository.save(orderMaster);
        if (saveResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResponseCode.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDto);
            throw new SellException(ResponseCode.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(orderDetail -> CartDto.builder()
                        .productId(orderDetail.getProductId())
                        .productQuantity(orderDetail.getProductQuantity())
                        .build()).collect(Collectors.toList());
        productInfoService.increaseStock(cartDtoList);
        //TODO 如果已支付需要退款
        if (orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS)) {

        }
        return orderDto;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode().byteValue())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResponseCode.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode().byteValue());
        OrderMaster orderMaster = OrderMaster.builder().build();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster saveResult = masterRepository.save(orderMaster);
        if (saveResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResponseCode.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode().byteValue())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResponseCode.ORDER_STATUS_ERROR);
        }
        //判断订单支付状态
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode().byteValue())) {
            log.error("【订单支付完成】订单支付状态不正确，orderDto={}", orderDto);
            throw new SellException(ResponseCode.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode().byteValue());
        OrderMaster orderMaster = OrderMaster.builder().build();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster saveResult = masterRepository.save(orderMaster);
        if (saveResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResponseCode.ORDER_UPDATE_FAIL);
        }
        return orderDto;
    }
}
