<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/6
  Time: 20:05
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
    <title>结算订单</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/buy.css"/>

    <script>
    </script>
</head>

<body>
<div class="buy-top">
    <h1 class="site-title">
        <a href="index.jsp">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>


<div style="margin-bottom: 10px;">
			<span class="buy-name">${u.name}
			</span>
</div>

<div class="info">
    <h3>填写个人收货信息</h3>
    <form id="info" action="forecreateOrder" method="post">
        <label class="label" for="receiver" style="margin-right: 56px">收件人</label>
        <input type="text" id="receiver" name="receiver" required ><br>
        <label class="label" for="address" style="margin-right: 3px">校内收货地址</label>
        <input type="text" id="address" name="address" placeholder="请准确填写宿舍楼号和宿舍号 例：15#307" required ><br>
        <label class="label" for="mobile" style="margin-right:-15px">收件人联系方式</label>
        <input type="text" id="mobile" name="mobile" required >

        <div class="submit-button">
            <span id="total-price">总价：${totalPrice}</span>
            <button id="buy-submit-button" type="submit" class="btn btn-lg">立即结算</button>
        </div>

    </form>
</div>

<div class="buy-bottom">
    <h3>确认订单信息</h3>
    <table class="buy-item-table">
        <tr>
            <th id="th1">图片</th>
            <th id="th2">名称</th>
            <th id="th3">卖家</th>
            <th id="th4">描述</th>
            <th id="th5">原价</th>
            <th id="th6">现价</th>
        </tr>
        <c:forEach items="${ois}" var="oi">
            <tr>
                <td><a href="foreitem?iid=${oi.item.id}"><img class="buy-pic" src="img/item/single/${oi.item.id}.jpg"/></a></td>
                <td><a href="foreitem?iid=${oi.item.id}" class="buy-item-name">${oi.item.name}</a></td>
                <td><span class="buy-item-mName">${oi.item.user.name}</span></td>
                <td><span class="buy-item-description">${oi.item.description}</span></td>
                <td><span class="item-originalPrice">${oi.item.originalPrice}</span></td>
                <td><span class="item-salePrice">${oi.item.salePrice}</span></td>
            </tr>
        </c:forEach>
    </table>
</div>



<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>

</body>

</html>
