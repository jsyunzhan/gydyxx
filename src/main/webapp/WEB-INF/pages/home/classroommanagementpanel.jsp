<%--
    致用课堂
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/10
  Time: 9:49
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
            src="<c:url value="/static/js/home/classroommanagementpanel.js"/>"></script>
</head>
<body class="easyui-layout">


<div data-options="region:'center'">
    <table id="classroomGrid"></table>
</div>

<!--新增窗口-->
<div id="addClassroomWin">
    <form id="addClassroomForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">致用校园标题

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height:50px;width: 300px" name="classroomTitle" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

        </table>

        <div style="padding-top: 2px; margin-right: 2px">
            <script id="containerAdd" name="classroomDetails" type="text/plain"></script>
        </div>

    </form>

    <div id="addClassroomWinFooter" style="text-align:center;padding:5px">
        <a id="addClassroomWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="addClassroomWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>

<!--修改窗口-->
<div id="editClassroomWin">
    <form id="editClassroomForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">致用校园标题

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height:50px;width: 300px" name="classroomTitle" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

        </table>

        <div style="padding-top: 2px; margin-right: 2px">
            <script id="containerEdit" name="classroomDetails" type="text/plain"></script>
        </div>

    </form>

    <div id="editClassroomWinFooter" style="text-align:center;padding:5px">
        <a id="editClassroomWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="editClassroomWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>



</body>
</html>
