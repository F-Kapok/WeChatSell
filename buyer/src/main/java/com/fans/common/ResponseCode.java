package com.fans.common;

import lombok.Getter;

/**
 * @EnumName ResponseCode
 * @Description:  响应代码
 * @Author fan
 * @Date 2018-11-23 11:29
 * @Version 1.0
 **/
@Getter
public enum ResponseCode {
    /**
     * 成功标识码 0
     */
    SUCCESS(0, "Response successfully"),
    /**
     * 失败标识码 1
     */
    ERROR(1, "Response error"),
    /**
     * 需要登录标识码 3
     */
    NEED_LOGIN(3, "Response need login"),
    /**
     * 参数不符合规定标识码 2
     */
    ILLEGAL_ARGUMENT(2, "Response argument illegality"),
    /**
     * 商品不存在标识码 10
     */
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    /**
     * 商品库存不正确标识码 11
     */
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    /**
     * 订单不存在标识码 12
     */
    ORDER_NOT_EXIST(12, "订单不存在"),
    /**
     * 订单详情不存在标识码 13
     */
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    /**
     * 订单状态不正确标识码 14
     */
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    /**
     * 订单更新失败标识码 15
     */
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    /**
     * 订单详情为空标识码 16
     */
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    /**
     * 订单支付状态不正确标识码 17
     */
    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),
    /**
     * 购物车为空标识码 18
     */
    CART_EMPTY(18, "购物车为空"),
    /**
     * 该订单不属于当前用户标识码 19
     */
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),
    /**
     * 微信公众账号方面错误标识码 20
     */
    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),
    /**
     * 微信支付异步通知金额校验不通过标识码 21
     */
    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),
    /**
     * 订单取消成功标识码 22
     */
    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),
    /**
     * 订单完结成功标识码 23
     */
    ORDER_FINISH_SUCCESS(23, "订单完结成功"),
    /**
     * 商品状态不正确标识码 24
     */
    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),
    /**
     * 登录失败, 登录信息不正确标识码 25
     */
    LOGIN_FAIL(25, "登录失败, 登录信息不正确"),
    /**
     * 登出成功标识码 26
     */
    LOGOUT_SUCCESS(26, "登出成功"),
    /**
     * 参数不正确标识码 27
     */
    PARAM_ERROR(27, "参数不正确"),

    ;
    private final Integer code;
    private final String desc;

    ResponseCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
