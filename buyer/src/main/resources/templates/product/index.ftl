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
                    <form role="form" method="post" action="/sell/seller/product/save" enctype="multipart/form-data">
                        <div class="form-group" hidden>
                            <label>商品ID</label>
                            <input name="productId" value="${(productInfo.productId)!''}" type="text"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>名称</label>
                            <input name="productName" value="${(productInfo.productName)!''}" type="text"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input name="productPrice" value="${(productInfo.productPrice)!''}" type="text"
                                   class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <#if (productInfo.productStock)??>
                                <input name="productStock" value="${(productInfo.productStock)?c}"
                                       type="text"
                                       class="form-control"/>
                            <#else>
                                <input name="productStock" value=""
                                       type="text"
                                       class="form-control"/>
                            </#if>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input name="productDescription" value="${(productInfo.productDescription)!''}" type="text"
                                   class="form-control"/>
                        </div>
                        <div class="form-group" style="margin-bottom: -20px">
                            <label>图片</label>
                            <img id='show' src="${(productInfo.productIcon)!''}" alt="" height="100" width="100">
                            <label for="file" class='btn btn-success' id="icoLabel">上传图片</label>
                            <input type="file" name="productIcon" id="file" accept="image/*"
                                   onchange="changepic()" class="inputfile" value="${(productInfo.productIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类目</label>
                            <select class="form-control" name="categoryType">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                            </#if>
                                    >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<style type="text/css">
    #show {
        margin-left: 25px;
    }

    #icoLabel {
        margin-left: 20px;
    }

    .inputfile {
        opacity: 0;
    }
</style>
<script>
    function changepic() {
        var reads = new FileReader();
        var f = document.getElementById('file').files[0];
        reads.readAsDataURL(f);
        reads.onload = function (e) {
            document.getElementById('show').src = this.result;
        };
    }
</script>
</html>
<#--<h1>${data.getTotalElements()}</h1>-->
<#--<#list data.getContent() as order>-->
<#--${order.orderId}<br/>-->
<#--</#list>-->