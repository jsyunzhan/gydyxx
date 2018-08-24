<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="icon" href="${APP_PATH}/static/images/favicon.ico" type="image/x-icon">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <link rel="stylesheet" href="${APP_PATH}/static/css/backstage/public.css">
    <link rel="stylesheet" href="${APP_PATH}/static/css/mail.css">
    <script src="${APP_PATH}/static/jq/jquery-1.7.2.min.js"></script>
    <script src="${APP_PATH}/static/js/mail.js"></script>
    <title>校长信箱</title>
</head>
<body>
    <div class="wrapper">
        <div class="banner">
            <img src="${APP_PATH}/static/images/mailbg.jpg">
        </div>
        <div class="bigLogin">
            <a href="${APP_PATH}/pc/homepage.jsp">
                <img src="${APP_PATH}/static/images/bigLogin.png">
            </a>
        </div>
        <div class="mailbox">
            <div class="title">校长信箱</div>
            <div class="invite">请你留言<div class="invite_bor"></div></div>
            <form id="emailForm" class="clearfix">
                <div class="message clearfix">
                    <div>姓名<span>*</span></div>
                    <input type="text" id="sendName" name="sendName">
                </div>
                <div class="message clearfix">
                    <div>邮箱<span>*</span></div>
                    <input type="text" id="sendEmail" name="sendEmail">
                </div>
                <div class="message clearfix">
                    <div>电话</div>
                    <input type="text" id="sendNumber" name="sendNumber">
                </div>
                <div class="message_area clearfix">
                    <div>留言内容<span>*</span></div>
                    <textarea id="sendDetails" name="sendDetails"></textarea>
                </div>
            </form>
            <div class="send">
                <div class="submit">提交</div>
                <div class="reset">重置</div>
            </div>
        </div>
    </div>

    <div class="timeLimit">
        <div class="colorLimit"></div>
        <div class="fontLimit">提交成功！<br>即将返回学校首页...</div>
    </div>
</body>
<script>
    var path  = '<%=request.getContextPath()%>';
</script>
</html>
