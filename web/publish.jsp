<%@ page import="bean.User" %>
<%@ page import="bean.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Item" %><%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/12/1
  Time: 12:20
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
    <title>发布闲置</title>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <link rel="stylesheet" href="css/fore/publish.css"/>

    <script>
    </script>

</head>

<body>

<div class="publish-top">
    <h1 class="site-title">
        <a href="index.jsp">校园二手交易平台</a>
    </h1>

    <h2 class="site-description">在这里，你能挑选需要的物品、结识有趣的灵魂。</h2>
</div>


<div style="margin-bottom: 10px;">
    <span class="publish-name">${u.name}&nbsp;&nbsp;发布闲置&nbsp;&nbsp;填写物品信息</span>
</div>

<div class="publish-bottom">
    <form id="form_1" action="forepublish" method="post" style="width: 450px; display: inline-block;">

        <div class="row">
            <div class="col-25">
                <label for="userName">物品持有者</label>
            </div>
            <div class="col-75">
                <input type="text" id="userName" name="userName" readonly="readonly" value="${u.name}">
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="name">物品名称</label>
            </div>
            <div class="col-75">
                <input type="text" id="name" name="name" required>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="categoryName">分类</label>
            </div>
            <div class="col-75">
                <select id="categoryName" name="categoryName" required>
                    <c:forEach items="${cs}" var="c">
                        <option>${c.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="originalPrice">原购买价格</label>
            </div>
            <div class="col-75">
                <input type="text" id="originalPrice" name="originalPrice" required>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="salePrice">现出售价格</label>
            </div>
            <div class="col-75">
                <input type="text" id="salePrice" name="salePrice" required>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="description">物品描述</label>
            </div>
            <div class="col-75">
                <textarea id="description" name="description" style="height: 150px;"></textarea>
            </div>
        </div>

        <div class="publish-submit-button">
            <button type="submit" class="btn btn-lg">下一步</button>
        </div>

    </form>

    <%--<form id="form_2" action="forepublishImage" method="post" enctype="multipart/form-data"--%>
    <%--style="width: 600px;height: 160px; display: inline-block; float: right;">--%>
    <%--<div class="row" style="height: 120px;">--%>
    <%--<div class="col-25">--%>
    <%--<label for="itemImage_single">物品主图片</label>--%>
    <%--</div>--%>
    <%--<div class="col-75" style="margin-top: 20px;">--%>
    <%--<input type="file" id="itemImage_single" name="itemImage_single" accept="image/*" required--%>
    <%--onchange="select_single(this)" style="width: 50%;float: left;">--%>
    <%--<img src="" id="img-single"/>--%>
    <%--</div>--%>
    <%--<div>--%>
    <%--<input type="hidden" name="type" value="type_single"/>--%>
    <%--<input type="hidden" name="iid" value="${i.id}"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</form>--%>

    <%--<form id="form_3" action="forepublishImage" method="post" enctype="multipart/form-data"--%>
    <%--style="width: 600px;margin-top: -360px; float: right;">--%>

    <%--<div class="row">--%>
    <%--<div class="col-25" style="height: 370px;">--%>
    <%--<label>物品细节图片<br>(最多选择5张)</label>--%>
    <%--</div>--%>
    <%--<div class="col-75" style="margin-top: 20px;">--%>
    <%--<input type="file" id="itemImage_detail_1" name="itemImage_detail_1" accept="image/*"--%>
    <%--onchange="select_1(this)" style="width: 50%;float: left; ">--%>
    <%--<img src="" id="img-1"/>--%>
    <%--</div>--%>

    <%--<div class="col-75" style="margin-top: 20px;">--%>
    <%--<input type="file" id="itemImage_detail_2" name="itemImage_detail_2" accept="image/*"--%>
    <%--onchange="select_2(this)" style="width: 50%;float: left;">--%>
    <%--<img src="" id="img-2"/>--%>
    <%--</div>--%>

    <%--<div class="col-75" style="margin-top: 20px;">--%>
    <%--<input type="file" id="itemImage_detail_3" name="itemImage_detail_3" accept="image/*"--%>
    <%--onchange="select_3(this)" style="width: 50%;float: left;">--%>
    <%--<img src="" id="img-3"/>--%>
    <%--</div>--%>

    <%--<div class="col-75" style="margin-top: 20px;">--%>
    <%--<input type="file" id="itemImage_detail_4" name="itemImage_detail_4" accept="image/*"--%>
    <%--onchange="select_4(this)" style="width: 50%;float: left;">--%>
    <%--<img src="" id="img-4"/>--%>
    <%--</div>--%>

    <%--<div>--%>
    <%--<input type="hidden" name="type" value="type_single"/>--%>
    <%--<input type="hidden" name="iid" value="${i.id}"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--</form>--%>

</div>

<div style="position: fixed ;bottom: 80px;right: 110px;">
    <a href="index.jsp"><img src="img/fore/index.png"></a>
</div>

</body>

</html>

