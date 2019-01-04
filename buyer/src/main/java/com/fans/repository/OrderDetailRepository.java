package com.fans.repository;

import com.fans.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @InterfaceName OrderDetailRepository
 * @Description:
 * @Author fan
 * @Date 2019-01-03 13:49
 * @Version 1.0
 **/
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
    
}
