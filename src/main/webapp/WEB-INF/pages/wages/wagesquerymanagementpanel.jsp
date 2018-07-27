<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/27
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
    <style>
        .wrapper{
            width: 100%;
            overflow-x: auto;
            height: 100%;
        }
        .pay{
            white-space: nowrap;
            font-size: 0;
            margin: 10px;
        }
        .pay .payNum{
            width: 120px;
            line-height: 40px;
            font-size: 20px;
            text-align: center;
            border-bottom: 1px solid #000;
            font-size: 14px;
            box-sizing: border-box;
            white-space: normal;
            word-wrap: break-word;
            word-break: break-all;
            overflow: hidden;
            display: inline-block;
        }
        .payNum p{
            border: 1px solid #000;
            border-bottom: 0;
            margin: 0;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>
    <jsp:include flush="true" page="/WEB-INF/pages/commons/servercommonjs.jsp"/>

    <script type="text/javascript"
            src="<c:url value="/static/js/wages/wagesquerymanagementpanel.js"/>"></script>
</head>
<body class="easyui-layout">

    <div class="wrapper">
    </div>

</body>
</html>
