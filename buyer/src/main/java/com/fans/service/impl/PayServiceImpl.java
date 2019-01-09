package com.fans.service.impl;

import com.fans.dto.OrderDto;
import com.fans.service.interfaces.IPayService;
import com.fans.uitls.JsonMapper;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
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

    @Override
    public void create(OrderDto orderDto) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 request : {}", JsonMapper.obj2String(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】 response : {}", JsonMapper.obj2String(payResponse));
    }
}
