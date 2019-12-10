<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/6
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8"/>
    <title>购物车</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/cart.css"/>

    <script>
        $(function () {
            $("#buy").click(function () {
                var url = "forebuy?";
                <c:forEach items="${ois}" var="oi">
                    url = url + "oiid=${oi.id}&";
                </c:forEach>
                $("#buy-url").attr("href",url);
            });
        });
    </script>
</head>

<body>
<div class="cart-top">
    <h1 class="site-title">
        <a href="forehome">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>


<div style="margin-bottom: 10px;">
    <span class="cart-name">${u.name}&nbsp;购物车</span>
</div>

<div class="cart-bottom">
    <table class="cart-item-table">
        <thead>
        <tr>
            <th id="th1">图片</th>
            <th id="th2">名称</th>
            <th id="th3">描述</th>
            <th id="th4">金额</th>
            <th id="th5">操作</th>
        </tr>
        <c:forEach items="${ois}" var="oi">
            <tr>
                <td><a href="foreitem?iid=${oi.item.id}"><img class="cart-pic" src="img/item/single/${oi.item.id}.jpg"/></a>
                </td>
                <td><a href="foreitem?iid=${oi.item.id}" class="personal-item-name">${oi.item.name}</a></td>
                <td><span class="cart-item-description">${oi.item.description}</span></td>
                <td><span class="item-price">${oi.item.salePrice}元</span></td>
                <td><a class="cart-delete" href="forecartDelete?oiid=${oi.id}">删除</a></td>
            </tr>
        </c:forEach>

        </thead>
    </table>
</div>

<div class="cart-footer">
    <a id="buy-url" href="">
        <button id="buy" class="btn btn" style="float: right; font-size: 18px;
				 margin-top: 10px;margin-bottom: 20px; color: #333333">结&nbsp;&nbsp;算
        </button>
    </a>
</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>


</body>

</html>

