<%--
家校资源
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/12
  Time: 16:00
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
            src="<c:url value="/static/js/home/resourcesmanagementpanel.js"/>"></script>
</head>
<body class="easyui-layout">


<div data-options="region:'center'">
    <table id="resourcesGrid"></table>
</div>

<!--新增窗口-->
<div id="addResourcesWin">
    <form id="addResourcesForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">家校资源标题

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height:50px;width: 300px" name="resourcesTitle" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th width="120"><label class="control-label required-mark" style="width:110px"
                                       required="required">家校资源内容
                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height: 200px;width: 300px" name="resourcesDetails" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

        </table>

    </form>

    <div id="addResourcesWinFooter" style="text-align:center;padding:5px">
        <a id="addResourcesWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="addResourcesWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>

<!--修改窗口-->
<div id="editResourcesWin">
    <form id="editResourcesForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">家校资源标题

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height:50px;width: 300px" name="resourcesTitle" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th width="120"><label class="control-label required-mark" style="width:110px"
                                       required="required">家校资源内容

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height: 200px;width: 300px" name="resourcesDetails" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

        </table>

    </form>

    <div id="editResourcesWinFooter" style="text-align:center;padding:5px">
        <a id="editResourcesWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="editResourcesWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>



</body>
</html>
