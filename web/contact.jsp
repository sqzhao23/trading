<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/7
  Time: 16:33
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
    <title>完善信息</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <style>
        body {
            background-image: url(img/fore/background.jpeg);
            background-repeat:no-repeat;
            background-attachment:fixed;
            font-family: "微软雅黑";
        }

        .top {
            left: 0;
            right: 0;
            margin: auto;
            height: 150px;
            width: 1240px;
        }

        .top .site-title {
            margin-top: 30px;
            font-size: 55px;
        }

        .top .site-title a{
            color: #396486;
            text-decoration: none;
        }

        .top .site-description {
            float: right;
            color: #396486;
        }

        .info {
            left: 0;
            right: 0;
            margin: auto;
            padding-left: 20px;
            padding-right: 50px;
            height: 180px;
            width: 1200px;
        }

        input[type=text] {
            width: 35%;
            padding: 12px 20px;
            margin: 3px 50px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .label {
            margin-left: 50px;
            font-family: "微软雅黑";
            font-size: 18px;
            color: #333333;
        }

        #name {
            margin-left: 15px;
        }

        #button {
            margin-top: 10px;
            margin-left: 455px;
            width: 140px;
        }
    </style>
</head>

<body>
<div class="top">
    <h1 class="site-title">
        <a href="index.jsp">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>

<div class="info">
    <h3>完善个人信息</h3>
    <form action="forecontact" method="post">
        <label class="label" for="name" style="margin-right: 56px">用户名</label>
        <input type="text" id="name" name="name" readonly="readonly" value="${u.name}"><br>
        <label class="label" for="contact" style="margin-right: 3px">联系方式</label>
        <input type="text" id="contact" name="contact" placeholder="请准确填写电话或者微信号"><br>
        <button id="button" type="submit" class="btn btn-lg">提交</button>
    </form>
</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>

</body>

</html>
