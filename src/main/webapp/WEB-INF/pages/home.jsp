<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <link rel="shortcut icon" href="${APP_PATH}/static/images/backstage/backstage/favicon.ico">
    <%--以上三个meta不可或缺--%>
    <link rel="stylesheet" href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <%--<link href="${APP_PATH}/static/easyui/themes/default/easyui.css" rel="stylesheet" />--%>
    <%--<link href="${APP_PATH}/static/easyui/themes/icon.css" rel="stylesheet" />--%>
    <link rel="stylesheet" href="${APP_PATH}/static/css/backstage/public.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/backstage/home.css">
    <script src="${APP_PATH}/static/jq/jquery-1.12.4.min.js"></script>
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <%--<script src="${APP_PATH}/static/easyui/jquery.min.js"></script>--%>
    <%--<script src="${APP_PATH}/static/easyui/jquery.easyui.min.js"></script>--%>
    <script src="${APP_PATH}/static/js/public.js"></script>
    <script src="${APP_PATH}/static/js/backstage/home.js"></script>
    <title>后台首页</title>
    <jsp:include flush="true" page="/WEB-INF/pages/commons/servercommonjs.jsp"/>
</head>
<body class="b_gray2">
<div class="container-fluid p0">
    <div class="tit_home row m0 pr white" style="display: block;">
        <img src="${APP_PATH}/static/images/backstage/tit_bg.jpg" class="tit_bg">
        <div class="pa title_project">高邮市第一实验小学管理系统</div>
        <div class="pa personal" style="background: url(123.png)">
            <div class="clearfix">
                <div style="width:30px; height:30px;">
                    <img src="${APP_PATH}/static/images/backstage/person.png">
                </div>
                <div style="width:70px;line-height: 30px;">${roleName}</div>
                <div style="width:16px; height: 16px;margin: 7px;">
                    <img src="${APP_PATH}/static/images/backstage/white_down.png">
                </div>
            </div>
            <ul class="dropdown-menu personal_ul">
                <li>
                    <a id="editPassWord" href="javascript:;"><span><img src="${APP_PATH}/static/images/backstage/modify.png"></span>修改密码</a>
                </li>
                <li>
                    <a href="${APP_PATH}/shiro/logout"><span><img src="${APP_PATH}/static/images/backstage/signOut.png"></span>退出登录</a>

                </li>
            </ul>
        </div>
    </div>
    <div class="clearfix">
        <div class="scroll_hide">
            <div class="sidebar tc white"></div>
        </div>
        <div class="main_content">
            <div id="content" class="easyui-tabs">
            </div>
        </div>
    </div>
</div>

<!--修改密码窗口-->
<div id="editPassWordWin">
    <form id="editPassWordForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">


            <tr>
                <th width="100"><label class="control-label required-mark" style="width:100px"
                                       required="required">密码

                </label>
                </th>
                <td width="200"><input id="password" class="easyui-textbox control" type="password" name="password" required="required"
                                       data-options="prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th width="100"><label class="control-label required-mark" style="width:100px"
                                       required="required">确认密码

                </label>
                </th>
                <td width="200"><input id="confirmPassword" class="easyui-textbox control" type="password" required="required"
                                       data-options="prompt:'请输入...'"/></td>
            </tr>

        </table>
    </form>

    <div id="editPassWordWinFooter" style="text-align:center;padding:5px">
        <a id="editPassWordWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="editPassWordWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>
</body>
<script>
    var roleId = "${roleId}";

    var path  = '<%=request.getContextPath()%>';

</script>
</html>
