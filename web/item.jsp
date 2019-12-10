<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/6
  Time: 10:02
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
    <title>物品详情</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/item.css"/>

    <script>
        $(function () {
            $("#cart").click(function () {
                <c:if test="${!empty u}">
                $("#cart-href").attr("href","forecartAdd?iid=${i.id}");
                </c:if>

                <c:if test="${empty u}">
                alert("请登录后加入购物车！");
                $("#cart-href").attr("href","login.jsp");
                </c:if>
            });

            $("#buyone").click(function () {
                <c:if test="${!empty u}">
                $("#buyone-href").attr("href","forebuyone?iid=${i.id}");
                </c:if>

                <c:if test="${empty u}">
                alert("请登录后结算物品！");
                $("#buyone-href").attr("href","login.jsp");
                </c:if>
            });

        })
    </script>
</head>

<body>
<div class="item-top">
    <h1 class="site-title">
        <a href="forehome">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>

<div class="item-bottom">
			<span style="margin-right: 800px;font-size: 18px;">卖家：
				<a href="forevisitor?user_mid=${user_m.id}" style="text-decoration: none;">${user_m.name}</a>
	        </span>
    <div class="left">
        <img class="item-pic" src="img/item/single/${i.id}.jpg"/>
        <p class="item-name">${i.name}</p>
        <p class="price"><span>${i.salePrice}元</span><i>${i.originalPrice}元</i></p>
    </div>
    <div class="right">
        <p class="item-description">详细描述：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${i.description}</p>
        <a id="cart-href" href="">
            <button id="cart" class="button btn">加入购物车</button>
        </a>

        <a id="buyone-href" href="">
            <button id="buyone" class="button btn">立即结算</button>
        </a>
    </div>
</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>

</body>

</html>
