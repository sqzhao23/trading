<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/11/29
  Time: 10:35
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
    <title>校园二手交易平台</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/index.css"/>

    <script>
        $(function () {
            $("#publish").click(function () {

                <c:if test="${empty u}">
                    alert("请登录后发布闲置物品！")
                    $("#publish_href").attr("href","login.jsp");
                </c:if>

                <c:if test="${!empty u}">

                    <c:if test="${!empty u.contact}">
                        $("#publish_href").attr("href","forebeforePublish");
                    </c:if>

                    <c:if test="${empty u.contact}">
                        alert("请完善联系方式后发布闲置物品！")
                        $("#publish_href").attr("href","contact.jsp");
                    </c:if>

                </c:if>
            });
        });
    </script>
</head>

<body>
<div class="site-warpper">
    <header class="site-header-outer">
        <div class="inner">
            <h1 class="site-title">
                <a href="">校园二手交易平台</a>
            </h1>
            <h2 class="site-description">
                在这里，你能挑选需要的物品、结识有趣的灵魂。
            </h2>

            <div class="site-search">
                <form action="foresearch" method="post" style="display: inline-block">
                    <input type="text" id="keyword" name="keyword" />
                    <button type="submit" id="search-button" class="button btn">搜索</button>
                </form>
                <a id="publish_href" href="">
                    <button id="publish" class="btn btn">发布闲置</button>
                </a>
                <div class="info">
                    <c:if test="${!empty u}">
                        <a href="login.jsp">${u.name}</a>
                        <a href="forepersonal?uid=${u.id}">个人中心</a>
                    </c:if>

                    <c:if test="${empty u}">
                        <a href="login.jsp">登录</a>
                    </c:if>

                    <a href="forecart">购物车</a>
                </div>

            </div>
            <nav class="site-nav">
                <ol class="breadcrumb" style="margin-top: 60px; font-size: 15px;">
                    <c:forEach items="${cs}" var="c">
                        <li>
                            <a href="foresearchByCategory?cid=${c.id}&cName=${c.name}">${c.name}</a>
                        </li>
                    </c:forEach>
                </ol>
            </nav>
        </div>
    </header>
</div>

<div class="box-container">
    <c:forEach items="${is}" var="i">
        <div class="good-item">
            <a href="foreitem?iid=${i.id}" class="pic"><img src="img/item/single/${i.id}.jpg"/></a>
            <h3 class="name"><a href="foreitem?iid=${i.id}">${i.name}</a></h3>
            <h4 class="desc">${i.description}</h4>
            <p class="price"><span>${i.salePrice}</span><i>${i.originalPrice}</i></p>
        </div>
    </c:forEach>
    <div style="position: absolute; bottom: 10px; width: 100%;
				text-align: center;font-family: '微软雅黑'; font-size: 17px;">
        <span>浏览更多首页随机物品请</span>
        <a href="">刷新</a>
    </div>
</div>

<div style="width: 100% ;text-align: center;font-family: '微软雅黑';
			margin-top: 30px;
			margin-bottom:15px;font-size: 17px;">
    <a target="_blank"
       href="https://github.com/sqzhao23">
        <img src="img/fore/github.ico"
             width="20px" height="20px" class="img-circle">GitHub</a>
    by sqzhao23
</div>

</body>

</html>
