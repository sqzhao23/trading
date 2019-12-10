<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/8
  Time: 11:26
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


    <script>
        $(function () {
            <c:if test="${msg =='success'}">
                $("#success").css("visibility","visible");
            </c:if>

            <c:if test="${msg =='fail'}">
                $("#fail").css("visibility","visible");
            </c:if>
        })
    </script>

    <style>
        body {
            background-image: url(img/fore/background.jpeg);
            font-family: "微软雅黑";
        }


        .addResult-top {
            left: 0;
            right: 0;
            margin: auto;
            height: 180px;
            width: 1240px;
        }

        .addResult-top .site-title {
            margin-top: 30px;
            font-size: 55px;
        }

        .addResult-top .site-title a{
            color: #396486;
            text-decoration: none;
        }

        .addResult-top .site-description {
            float: right;
            color: #396486;
        }

        .addResult-bottom {
            text-align: center;
            font-size: 22px;
            left: 0;
            right: 0;
            margin: auto;
            padding-top: 90px;
            padding-left: 50px;
            padding-bottom: 20px;
            padding-right: 50px;
            height: 520px;
            width: 1200px;
        }
    </style>
</head>

<body>
<div class="addResult-top">
    <h1 class="site-title">
        <a href="forehome">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>

<div class="addResult-bottom">

    <div id="success" style="visibility: hidden">
        <span>加入购物车成功！立即
            <a href="forecart" style="color: #333333">
                <button id="success-button">前往</button>
            </a>购物车！
        </span>
    </div>

    <div id="fail" style="visibility: hidden">
        <span>加入购物车失败！你的购物车里已经有这件物品啦！</span>
    </div>

</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>

</body>

</html>
