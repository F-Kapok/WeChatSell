package com.fans.service.impl;

import com.fans.common.OrderStatusEnum;
import com.fans.common.PayStatusEnum;
import com.fans.dto.OrderDto;
import com.fans.pojo.OrderDetail;
import com.fans.service.interfaces.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {
    private final String BUYER_OPENID = "110101";
    private final String ORDER_ID = "154650439020747";
    private final String BUYER_ID = "110101";
    @Resource(name = "iOrderService")
    private IOrderService iOrderService;

    @Test
    public void create() {
        OrderDto orderDTO = new OrderDto();
        orderDTO.setBuyerName("kapok");
        orderDTO.setBuyerAddress("北京市");
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = Lists.newArrayList();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1546421049882");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("157875196366160022");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDto result = iOrderService.create(orderDTO);
        log.info("【创建订单】result={}", result);
        assertNotNull(result);
    }

    @Test
    public void getOne() {
        OrderDto one = iOrderService.getOne(ORDER_ID);
        assertNotNull(one);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderDto> list = iOrderService.findList(BUYER_ID, pageRequest);
        assertTrue(list.getTotalElements() > 0);
    }

    @Test
    public void cancel() {
        OrderDto orderDto = iOrderService.getOne(ORDER_ID);
        OrderDto result = iOrderService.cancel(orderDto);
        assertEquals(OrderStatusEnum.CANCEL.getCode().toString(), result.getOrderStatus().toString());
    }

    @Test
    public void finish() {
        OrderDto orderDto = iOrderService.getOne(ORDER_ID);
        OrderDto result = iOrderService.finish(orderDto);
        assertEquals((byte) result.getOrderStatus(), OrderStatusEnum.FINISHED.getCode().byteValue());
    }

    @Test
    public void paid() {
        OrderDto orderDto = iOrderService.getOne(ORDER_ID);
        OrderDto result = iOrderService.paid(orderDto);
        assertEquals("验证通过", (byte) result.getPayStatus(), PayStatusEnum.SUCCESS.getCode().byteValue());
    }

    @Test
    public void findList1() {
        PageRequest pageRequest = PageRequest.of(1, 2);
        Page<OrderDto> page = iOrderService.findList(pageRequest);
        assertTrue(page.getTotalElements() != 0);
    }
}