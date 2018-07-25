<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/26
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/backstage/public.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/backstage/login.css">
    <script src="${APP_PATH}/static/jq/jquery-1.7.2.min.js"></script>
    <script src="${APP_PATH}/static/js/public.js"></script>
    <title>后台登录</title>
    <jsp:include flush="true" page="/WEB-INF/pages/commons/servercommonjs.jsp"/>
    <script type="text/javascript" src="<c:url value="/static/js/backstage/login.js"/>"></script>

</head>
<body>
    <div class="container-fluid m0 p0">
        <div class="banner">
            <img src="${APP_PATH}/static/images/backstage/login_banner.jpg">
        </div>
        <div class="bigLogin">
            <a href="${APP_PATH}/pc/homepage.jsp">
                <img src="${APP_PATH}/static/images/backstage/bigLogin.png">
            </a>
        </div>
        <div class="login">
            <img src="${APP_PATH}/static/images/backstage/form_bg.png">
            <div class="loginForm">
                <div class="mauto logo">
                    <img src="${APP_PATH}/static/images/backstage/logo.png">
                </div>
                <div class="login_font tc brown">后台登陆</div>
                <form id="loginForm">
                    <div class="mtb20 pr b_white">
                        <div class="icon pa">
                            <img src="${APP_PATH}/static/images/backstage/ic_username.png">
                        </div>
                        <input type="text" placeholder="请输入用户名" name="loginName" class="btn w100 bor_gray b_white username tl">
                    </div>
                    <div class="mtb20 pr b_white">
                        <div class="icon pa">
                            <img src="${APP_PATH}/static/images/backstage/ic_password.png">
                        </div>
                        <input type="password" placeholder="请输入密码" name="password" class="btn w100 bor_gray b_white password tl">
                    </div>
                    <div class="mtb20 f12 tc gray_1" id="userError">（请输入用户名及密码）</div>
                </form>
                <div class="mtb20">
                    <input id="loginSub" type="submit" class="btn btn-primary w100" value="登&nbsp;&nbsp;录">
                </div>
            </div>
        </div>
    </div>
</body>
</html>
