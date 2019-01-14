<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-7 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>订单总金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${data.orderId}</td>
                            <td>${data.orderAmount}</td>
                            <td>${data.getOrderStatusEnum().getDesc()}</td>
                            <td>${data.getPayStatusEnum().getDesc()}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品ID</th>
                            <th>商品名称</th>
                            <th>价格</th>
                            <th>数量</th>
                            <th>总额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list data.orderDetailList as orderDetail>
                            <tr>
                                <td>${orderDetail.productId}</td>
                                <td>${orderDetail.productName}</td>
                                <td>${orderDetail.productPrice}</td>
                                <td>${orderDetail.productQuantity}</td>
                                <td>${orderDetail.productQuantity * orderDetail.productPrice}</td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <#if data.getOrderStatusEnum().desc == "新订单">
                    <a href="/sell/seller/order/finish?orderId=${data.orderId}" type="button"
                       class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${data.orderId}" type="button"
                       class="btn btn-default btn-danger">取消订单</a>
                </#if>
            </div>
        </div>
    </div>
</div>

</body>
</html>