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
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <title>Title</title>
    <jsp:include flush="true" page="/WEB-INF/pages/commons/servercommonjs.jsp"/>
    <script type="text/javascript"
            src="<c:url value="/static/js/login.js"/>"></script>
</head>
<body>
    <form id="loginForm">
            <div>
                账号：<input type="text" name="loginName">
            </div>
            <div>
                密码：<input type="password" name="password">
            </div>
            <div>
                <input id="loginSub" type="button" value="登录">
            </div>
    </form>
</body>
</html>
