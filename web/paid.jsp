<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/7
  Time: 17:12
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
    <title>订单完成</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/paid.css"/>

    <script>
    </script>
</head>

<body>
<div class="paid-top">
    <h1 class="site-title">
        <a href="index.jsp">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>


<div style="margin-bottom: 10px;">
			<span class="paid-name">${u.name}
            </span>
</div>

<div class="info">
    <h2>订单已完成付款！</h2>
    <span class="th">订单编号</span> <span style="margin-left: 100px">${o.orderCode}</span><br>
    <span class="th">收件人</span> <span style="margin-left: 120px">${o.receiver}</span><br>
    <span class="th">收件地址</span> <span style="margin-left: 100px">${o.address}</span><br>
    <span class="th">收件人联系方式</span> <span style="margin-left: 40px">${o.mobile}</span><br>
    <span class="th">订单创建日期</span> <span style="margin-left: 60px">${o.createDate}</span><br>
</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>

</body>

</html>
