<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/19
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
    <jsp:include flush="true" page="/WEB-INF/pages/commons/servercommonjs.jsp"/>

    <script type="text/javascript"
            src="<c:url value="/static/js/pc/zyxiaoyuan/tzggdetails.js"/>"></script>
</head>
<body>

</body>
<script>
    var entity = "${entity}";
    console.log("1");
    console.log(entity);
</script>
</html>
