<%--
  Created by IntelliJ IDEA.
  User: sqzhao
  Date: 2019/11/29
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>声明</title>
    <meta http-equiv="content-type" content="text/html" ; charset="UTF-8"/>
    <script>
        var t = 5;
        setInterval("jump()", 1000);

        function jump() {
            if (t == 0) {
                location = "index.html";
            }
            document.getElementById('show').innerHTML = "" + t + "秒后跳转到登录界面";
            t--;
        }
    </script>
    <style>
        body {
            background-image: url(img/fore/background.jpeg);
        }

        .container {
            height: 500px;
            width: 1000px;
            position: relative;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            margin: auto;
        }

        p {
            margin-top: 50px;
            font-size: 60px;
            text-align: center;
            font-family: "微软雅黑";
        }
    </style>

</head>

<body>
<div class="container">
    <p>声&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;明</p>
    <p>本网站上的内容仅供本人学习使用</p>
</div>
<div align="center">
    <span id="show" style="font-size: 150%;"></span>
</div>
</body>

</html>
