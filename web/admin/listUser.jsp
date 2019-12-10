<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../menu/back/Header.jsp"%>
<%@include file="../menu/back/Navigator.jsp"%>
<script>
</script>

<title>用户管理</title>

<div class="workingArea">
    <h1 class="label label-info" >用户管理</h1>

    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>用户名称</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${us}" var="u">

                <tr>
                    <td>${u.id}</td>
                    <td>${u.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="../menu/back/Footer.jsp"%>
