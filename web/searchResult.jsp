<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/6
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix='fmt' %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>搜索结果</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/searchResult.css" />

    <script>
    </script>
</head>

<body>
<div class="site-warpper">
    <header class="site-header-outer">
        <div class="inner">
            <h1 class="site-title">
                <a href="forehome">校园二手交易平台</a>
            </h1>
            <h2 class="site-description">
                在这里，你能挑选需要的物品、结识有趣的灵魂。
            </h2>

            <div class="site-search">
                <form action="foresearch" method="post" style="display: inline-block">
                    <input type="text" id="keyword" name="keyword" />
                    <button type="submit" id="search-button" class="button btn">搜索</button>
                </form>
                <div class="info">
                    <c:if test="${!empty u}">
                        <a href="login.jsp">${u.name}</a>
                        <a href="forepersinal?uid=${u.id}">个人中心</a>
                    </c:if>

                    <c:if test="${empty u}">
                        <a href="login.jsp">登录</a>
                    </c:if>

                    <a href="forecart?uid=${u.id}">购物车</a>
                </div>

            </div>

            <div class="item-outcome">
                <span>以下是“${keyword}”的搜索结果：</span>
            </div>

        </div>
    </header>
</div>

<div class="box-container">
    <c:forEach items="${is}" var="i">
        <div class="good-item">
            <a href="foreitem?iid=${i.id}" class="pic"><img src="img/item/single/${i.id}.jpg"/></a>
            <h3 class="name"><a href="">${i.name}</a></h3>
            <h4 class="desc">${i.description}</h4>
            <p class="price"><span>${i.salePrice}</span><i>${i.originalPrice}</i></p>
        </div>
    </c:forEach>
</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>

</body>

</html>
