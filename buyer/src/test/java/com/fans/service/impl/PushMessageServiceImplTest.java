package com.fans.service.impl;

import com.fans.dto.OrderDto;
import com.fans.service.interfaces.IOrderService;
import com.fans.service.interfaces.IPushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {
    @Resource(name = "iPushMessageService")
    private IPushMessageService pushMessageService;
    @Resource(name = "iOrderService")
    private IOrderService orderService;

    @Test
    public void orderStatusPush() {
        OrderDto orderDto = orderService.getOne("154718578599028");
        pushMessageService.orderStatusPush(orderDto);
    }
}