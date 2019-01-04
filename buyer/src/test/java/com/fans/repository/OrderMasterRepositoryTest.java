package com.fans.repository;

import com.fans.pojo.OrderMaster;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    private final static String OPENID = "110101";
    @Resource(type = com.fans.repository.OrderMasterRepository.class)
    private OrderMasterRepository masterRepository;

    @Test
    public void save() {

        OrderMaster master = OrderMaster.builder()
                .orderId("5454")
                .buyerName("kapok")
                .buyerPhone("18599999999")
                .buyerAddress("北京市")
                .buyerOpenid("110101")
                .orderStatus((byte) 0)
                .payStatus((byte) 0)
                .orderAmount(new BigDecimal(2.3))
                .build();
        OrderMaster save = masterRepository.save(master);
        assertNotNull(save);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderMaster> byBuyerOpenid = masterRepository.findByBuyerOpenid(OPENID, pageRequest);
        assertNotNull(byBuyerOpenid);
    }
}