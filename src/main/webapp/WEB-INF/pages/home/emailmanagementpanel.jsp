<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/13
  Time: 9:17
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
            src="<c:url value="/static/js/home/emailmanagementpanel.js"/>"></script>
</head>
<body class="easyui-layout">


<div data-options="region:'center'">
    <table id="emailGrid"></table>
</div>

<!--查看详情窗口-->
<div id="detailsEmailWin">
    <form id="detailsEmailForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">


            <tr>
                <th width="120"><label class="control-label required-mark" style="width:110px"
                                       required="required">姓名

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" name="sendName" readonly="readonly"
                                       data-options="prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">邮箱

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" name="sendEmail" readonly="readonly"
                                       data-options="prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th width="120"><label class="control-label required-mark" style="width:110px"
                                       required="required">号码

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" name="sendNumber" readonly="readonly"
                                       data-options="prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">留言内容

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height: 200px;width: 300px" name="sendDetails" readonly="readonly"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

        </table>

    </form>

    <div id="detailsEmailWinFooter" style="text-align:center;padding:5px">
        <a id="detailsEmailWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>



</body>
</html>
