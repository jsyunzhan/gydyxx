<%--
  Created by IntelliJ IDEA.
  User: new
  Date: 2018/5/8
  Time: 8:20
  用户管理
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
    <jsp:include flush="true" page="/WEB-INF/pages/commons/servercommonjs.jsp"/>

    <script type="text/javascript"
            src="<c:url value="/static/js/system/accountmanagementpanel.js"/>"></script>
</head>
<body class="easyui-layout">

    <div data-options="region:'center'">
        <table id="accountGrid"></table>
    </div>

    <!--新增窗口-->
    <div id="addAccountWin">
        <form id="addAccountForm" class="control-form">
            <table class="table_list" style="text-align: right;margin:3px;width:99%">
                <tr>
                    <th width="100"><label class="control-label required-mark" style="width:100px"
                                           required="required">用户名

                    </label>
                    </th>
                    <td width="200"><input class="easyui-textbox control" name="userName" required="required"
                                           data-options="prompt:'请输入...'"/></td>
                </tr>

                <tr>
                    <th width="100"><label class="control-label required-mark" style="width:100px"
                                           required="required">登录名

                    </label>
                    </th>
                    <td width="200"><input class="easyui-textbox control" name="loginName" required="required"
                                           data-options="prompt:'请输入...'"/></td>
                </tr>

                <tr>
                    <th width="100"><label class="control-label required-mark" style="width:100px">角色名称</label>
                    </th>
                    <td width="200"><input id="addRoleId" class="easyui-textbox control" name="roleId" required="required"
                                           data-options="prompt:'请选择...'"/></td>
                </tr>

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

        <div id="addAccountWinFooter" style="text-align:center;padding:5px">
            <a id="addAccountWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
               style="margin-right:20px;">
                确认
            </a>
            <a id="addAccountWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
               class="easyui-linkbutton">
                取消
            </a>
        </div>
    </div>

    <!--修改窗口-->
    <div id="editAccountWin">
        <form id="editAccountForm" class="control-form">
            <table class="table_list" style="text-align: right;margin:3px;width:99%">
                <tr>
                    <th width="100"><label class="control-label required-mark" style="width:100px"
                                           required="required">用户名

                    </label>
                    </th>
                    <td width="200"><input class="easyui-textbox control" name="userName" required="required"
                                           data-options="prompt:'请输入...'"/></td>
                </tr>

                <tr>
                    <th width="100"><label class="control-label required-mark" style="width:100px"
                                           required="required">登录名

                    </label>
                    </th>
                    <td width="200"><input class="easyui-textbox control" name="loginName" required="required"
                                           data-options="prompt:'请输入...'"/></td>
                </tr>

                <tr>
                    <th width="100"><label class="control-label required-mark" style="width:100px">角色名称</label>
                    </th>
                    <td width="200"><input id="editRoleId" class="easyui-textbox control" name="roleId" required="required"
                                           data-options="prompt:'请选择...'"/></td>
                </tr>

                <tr>
                    <th width="100"><label class="control-label required-mark" style="width:100px"
                                           required="required">密码

                    </label>
                    </th>
                    <td width="200"><input id="editPassword" class="easyui-textbox control" type="password" name="password" required="required"
                                           data-options="prompt:'请输入...'"/></td>
                </tr>

                <tr>
                    <th width="100"><label class="control-label required-mark" style="width:100px"
                                           required="required">确认密码

                    </label>
                    </th>
                    <td width="200"><input id="editConfirmPassword" class="easyui-textbox control" type="password" required="required"
                                           data-options="prompt:'请输入...'"/></td>
                </tr>

            </table>
        </form>

        <div id="editAccountWinFooter" style="text-align:center;padding:5px">
            <a id="editAccountWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
               style="margin-right:20px;">
                确认
            </a>
            <a id="editAccountWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
               class="easyui-linkbutton">
                取消
            </a>
        </div>
    </div>

</body>
</body>
</html>
