<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../menu/back/Header.jsp"%>
<%@include file="../menu/back/Navigator.jsp"%>


<title>物品管理</title>

<div class="workingArea">

    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_item_list?cid=${c.id}">${c.name}</a></li>
        <li class="active">物品管理</li>
    </ol>

    <div class="listDataTableDiv">
        <table
                class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>物品名称</th>
                <th>图片</th>
                <th width="53px">原价格</th>
                <th width="80px">优惠价格</th>
                <th width="42px">物品描述</th>
                <th width="42px">管理</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${is}" var="i">
                <tr>
                    <td>${i.id}</td>
                    <td>${i.name}</td>
                    <td>
                        <c:if test="${!empty i.image}">
                            <img width="40px" src="img/item/${i.image.id}.jpg">
                        </c:if>
                    </td>
                    <td>${i.originalPrice}</td>
                    <td>${i.salePrice}</td>
                    <td>${i.description}</td>

                    <td><a deleteLink="true"
                           href="admin_item_delete?id=${i.id}"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="../menu/back/Footer.jsp"%>
