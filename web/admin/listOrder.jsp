<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../menu/back/Header.jsp"%>
<%@include file="../menu/back/Navigator.jsp"%>

<script>
    $(function(){
        $("button.orderPageCheckOrderItems").click(function(){
            var oid = $(this).attr("oid");
            $("tr.orderPageOrderItemTR[oid="+oid+"]").toggle();
        });
    });

</script>

<title>订单管理</title>

<div class="workingArea">
    <h1 class="label label-info" >订单管理</h1>
    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover1  table-condensed">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>订单编号</th>
                <th>状态</th>
                <th>金额</th>
                <th>数量</th>
                <th width="100px">卖家名称</th>
                <th width="100px">买家名称</th>
                <th>创建时间</th>
                <th>完成时间</th>
                <th width="120px">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${os}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.orderCode}</td>
                    <td>${o.statusDesc}</td>
                    <td>￥<fmt:formatNumber type="number" value="${o.totalPrice}" minFractionDigits="2"/></td>
                    <td align="center">${o.totalNumber}</td>
                    <td align="center" href="">${o.user.name}</td>
                    <td align="center" href="${o.bUrl}">${o.bName}</td>

                    <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${o.completeDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                    <td>
                        <button oid=${o.id} class="orderPageCheckOrderItems btn btn-primary btn-xs">查看详情</button>
                    </td>
                </tr>
                <tr class="orderPageOrderItemTR"  oid=${o.id}>
                    <td colspan="10" align="center">

                        <div  class="orderPageOrderItem">
                            <table width="800px" align="center" class="orderPageOrderItemTable">
                                <c:forEach items="${o.orderItems}" var="oi">
                                    <tr>
                                        <td align="left">
                                            <img width="40px" height="40px" src="img/item/${oi.item.image.id}.jpg">
                                        </td>

                                        <td>
                                            <a href="foreproduct?pid=${oi.product.id}">
                                                <span>${oi.product.name}</span>
                                            </a>
                                        </td>
                                        <td align="right">

                                            <span class="text-muted">${oi.number}个</span>
                                        </td>
                                        <td align="right">

                                            <span class="text-muted">单价：￥${oi.product.promotePrice}</span>
                                        </td>

                                    </tr>
                                </c:forEach>

                            </table>
                        </div>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<%@include file="../menu/back/Footer.jsp"%>
