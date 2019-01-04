package com.fans.repository;

import com.fans.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @InterfaceName OrderMasterRepository
 * @Description:
 * @Author fan
 * @Date 2019-01-03 13:47
 * @Version 1.0
 **/
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
    
}
