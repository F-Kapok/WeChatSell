<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

    <#include "../common/nav.ftl">

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group" hidden>
                            <label>类目ID</label>
                            <input name="categoryId" value="${(data.categoryId)!''}" type="number"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>名称</label>
                            <input name="categoryName" value="${(data.categoryName)!''}" type="text"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>类型</label>
                            <input name="categoryType" value="${(data.categoryType)!''}" type="number"
                                   class="form-control"/>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<#--<h1>${data.getTotalElements()}</h1>-->
<#--<#list data.getContent() as order>-->
<#--${order.orderId}<br/>-->
<#--</#list>-->