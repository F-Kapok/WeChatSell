package com.fans.service.interfaces;

import com.fans.dto.OrderDto;

/**
 * @InterfaceName IBuyerService
 * @Description: TODO
 * @Author fan
 * @Date 2019-01-04 14:04
 * @Version 1.0
 **/
public interface IBuyerService {
    /**
     * 买家查询订单详情
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDto buyerFindOrderDetail(String openid, String orderId);

    /**
     * 买家取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderDto buyerCancelOrder(String openid, String orderId);
}
