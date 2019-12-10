<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/7
  Time: 15:51
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
    <title>付款页面</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <style>
        body {
            background-image: url(img/fore/background.jpeg);
            font-family: "微软雅黑";
            font-size: 20px;
        }

        .pay {
            height: 500px;
            width: 1240px;
            margin: 0 auto;
            left: 0;
            right: 0;
            padding-top: 100px;
        }
        .pay-button, .pay-pic, .pay-text {
            text-align: center;
            width: 300px;
            margin: 0 auto;
            left: 0;
            right: 0;
            padding-top: 20px;
        }
    </style>
</head>
<body>
<div class="pay">
    <div class="pay-text">
        <span>建议当面交易<br>扫一扫付款（元）</span>
        <span>
        ￥<fmt:formatNumber type="number" value="${param.totalPrice}" minFractionDigits="2"/></span>
    </div>

    <div class="pay-pic">
        <img src="img/pay.png">
    </div>

    <div class="pay-button">
        <a href="forepayed?oid=${param.oid}">
            <button class="btn btn-lg" style="color: #333333">确认支付</button>
        </a>
    </div>
</div>

</body>
</html>
