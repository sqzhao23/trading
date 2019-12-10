<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/7
  Time: 15:09
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
    <title>订单中心</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/orderList.css"/>

    <script>
    </script>
</head>

<body>
<div class="orderList-top">
    <h1 class="site-title">
        <a href="index.jsp">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>


<div style="margin-bottom: 10px;">
			<span class="orderList-name">${u.name}&nbsp;&nbsp;个人中心
                <a href="forepersonal?uid=${u.id}">我的发布</a>
			</span>
</div>

<div class="orderList-bottom">
    <table class="orderList-item-table">
        <tr>
            <th id="th1">订单编号</th>
            <th id="th2">收件人</th>
            <th id="th3">收件地址</th>
            <th id="th4">收件人联系方式</th>
            <th id="th5">创建日期</th>
            <th id="th6">订单状态</th>
            <th id="th7">操作</th>
        </tr>
        <c:forEach items="${os}" var="o">
            <tr>
                <td><span class="orderList-orderCode">${o.orderCode}</span></td>
                <td><span class="orderList-receiver">${o.receiver}</span></td>
                <td><span class="orderList-address">${o.address}</span></td>
                <td><span class="orderList-mobile">${o.mobile}</span></td>
                <td><span class="orderList-createDate">${o.createDate}</span></td>
                <td><span class="orderList-status">${o.status}</span></td>
                <td><a class="orderList-operate" href="foreorderItem?oid=${o.id}">查看详情</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="forehome"><img src="img/fore/index.png"></a>
</div>

</body>

</html>
