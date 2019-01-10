package com.fans.service.impl;

import com.fans.common.OrderStatusEnum;
import com.fans.common.ResponseCode;
import com.fans.dto.OrderDto;
import com.fans.exception.SellException;
import com.fans.service.interfaces.IOrderService;
import com.fans.service.interfaces.IPayService;
import com.fans.uitls.BigDecimalUtil;
import com.fans.uitls.JsonMapper;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName PayServiceImpl
 * @Description:
 * @Author fan
 * @Date 2019-01-09 22:15
 * @Version 1.0
 **/
@Service(value = "iPayService")
@Slf4j
public class PayServiceImpl implements IPayService {
    private static final String ORDER_NAME = "微信点餐支付";
    @Resource(name = "bestPayService")
    private BestPayServiceImpl bestPayService;
    @Resource(name = "iOrderService")
    private IOrderService orderService;

    @Override
    public PayResponse create(OrderDto orderDto) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 发起支付, request : {}", JsonMapper.obj2String(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 发起支付, response : {}", JsonMapper.obj2String(payResponse));
        return payResponse;
    }

    @Override
    public PayResponse weChatNotify(String notifyData) {
        //1. 验证签名 *
        //2. 支付的状态 *
        //3. 支付金额
        //4. 支付人（下单人 == 支付人）
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知, payResponse={}", JsonMapper.obj2String(payResponse));
        OrderDto orderDto = orderService.getOne(payResponse.getOrderId());
        if (orderDto == null) {
            log.error("【微信支付】异步通知, 订单不存在, orderId={}", payResponse.getOrderId());
            throw new SellException(ResponseCode.ORDER_NOT_EXIST);
        }
        if (!BigDecimalUtil.equals(orderDto.getOrderAmount().doubleValue(), payResponse.getOrderAmount())) {
            log.error("【微信支付】异步通知, 订单金额不一致, orderId={}, 微信通知金额={}, 系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDto.getOrderAmount());
            throw new SellException(ResponseCode.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改订单状态
        orderService.paid(orderDto);
        return null;
    }

    @Override
    public RefundResponse refund(OrderDto orderDto) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 request= {}", JsonMapper.obj2String(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 response= {}", JsonMapper.obj2String(refundResponse));
        return refundResponse;
    }
}
