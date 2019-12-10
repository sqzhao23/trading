<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/2
  Time: 0:30
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
    <title>个人中心</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/personal.css"/>

    <script>
    </script>
</head>

<body>
<div class="personal-top">
    <h1 class="site-title">
        <a href="index.jsp">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>


<div style="margin-bottom: 10px;">
			<span class="personal-name">${u.name}&nbsp;&nbsp;个人中心
                <a href="foreorderList?uid=${u.id}">我的订单</a>
                <a href="forelogout">退出登录</a>
			</span>
</div>

<div class="personal-bottom">
    <table class="personal-item-table">
        <tr>
            <th id="th1">图片</th>
            <th id="th2">名称</th>
            <th id="th3">描述</th>
            <th id="th4">原价</th>
            <th id="th5">现价</th>
            <th id="th6">状态</th>
            <th id="th7">操作</th>
        </tr>
        <c:forEach items="${is}" var="i">
        <tr>
            <td><a href="foreitem?iid=${i.id}"><img class="personal-pic" src="img/item/single/${i.id}.jpg"/></a></td>
            <td><a href="foreitem?iid=${i.id}" class="personal-item-name">${i.name}</a></td>
            <td><span class="personal-item-description">${i.description}</span></td>
            <td><span class="item-originalPrice">${i.originalPrice}</span></td>
            <td><span class="item-salePrice">${i.salePrice}</span></td>
            <td><span class="item-status">${i.status}</span></td>
            <td><a class="personal-item-operate" href="foreitemDelete?iid=${i.id}">删除</a></td>
        </tr>
        </c:forEach>
    </table>
</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>

</body>

</html>

