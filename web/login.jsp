<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/11/29
  Time: 14:17
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
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <title>登录</title>

    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap/3.3.6/bootstrap.css"/>
    <script src="js/bootstrap/3.3.6/bootstrap.js"></script>

    <script>
        $(function () {
            <c:if test="${msg=='fail_1'}">
                $("span.errorMessage").html("账号或密码输入错误，请重新输入！");
                $("div.loginErrorMessageDiv").css("visibility", "visible");
            </c:if>

            $("#close").click(function () {
                $("div.loginErrorMessageDiv").css("visibility", "hidden");
            })
        })
    </script>


    <style>
        body {
            background-image: url(img/fore/background.jpeg);
            font-family: "微软雅黑";
        }

        a {
            text-decoration: none;
            color: #46697F;
        }

        a:hover {
            text-decoration: none;
        }

        .container {
            width: 800px;
            height: 400px;
            margin-top: 150px;
            text-align: center;
        }

        .loginErrorMessageDiv {
            color: #ffffff;
            text-align: center;
            font-family: '微软雅黑';
            font-size: 17px;
            visibility: hidden;
        }

        .inputname, .inputpassword {
            border-radius: 4px;
            line-height: 24px;
        }

        .login-lable {
            font-size: 20px;
        }

    </style>

</head>

<body>
<div class="container">
    <form action="forelogin" method="post">
        <p style="margin-bottom: 50px;">
            <a href="forehome" style="font-size: 60px;">校园二手交易平台</a>
        </p>

        <label for="name" class="login-lable">账号：</label>
        <input type="text" name="name" id="name" class="inputname" placeholder="请输入用户名" required/><br/>

        <label for="password" class="login-lable">密码：</label>
        <input type="password" name="password" id="password" class="inputpassword" placeholder="********"
               required/><br/>

        <button type="submit" class="button btn" style="width: 160px;
					background-color: #dddddd; margin-top: 20px;font-size: 16px;">登录
        </button>
        <br/>

        <span style="font-size: 16px;">没有账号？</span>
        <a href="register.jsp" style="font-size: 16px; color: #dddddd;">注册</a>
    </form>
</div>

<div class="loginErrorMessageDiv" id="mag-div">
    <span class="errorMessage"></span>
    <button id="close" class="btn btn" style="color: #000000; width: 70px">关闭</button>
</div>

<div align="center">
    <span id="show" style="font-size: 17px;"></span>
</div>

</body>

</html>