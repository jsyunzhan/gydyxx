<%--
    校园节庆
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/11
  Time: 10:54
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
            src="<c:url value="/static/js/home/celebratemanagementpanel.js"/>"></script>
</head>
<body class="easyui-layout">


<div data-options="region:'center'">
    <table id="celebrateGrid"></table>
</div>

<!--新增窗口-->
<div id="addCelebrateWin">
    <form id="addCelebrateForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">校园节庆标题

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height:50px;width: 300px" name="celebrateTitle" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th width="120"><label class="control-label required-mark" style="width:110px"
                                       required="required">校园节庆内容

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height: 200px;width: 300px" name="celebrateDetails" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

        </table>

        <div style="text-align:center;padding:10px 5px">
            <a id="pictureCelebrateUploadBtnAdd" href="javascript:void(0);" iconCls="l-btn-icon icon-add"
               class="easyui-linkbutton"
               style="margin-right:20px;">
                图片上传
            </a>

        </div>

        <div width="200" id="addPicture"></div>
    </form>

    <div id="addCelebrateWinFooter" style="text-align:center;padding:5px">
        <a id="addCelebrateWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="addCelebrateWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>

<!--修改窗口-->
<div id="editCelebrateWin">
    <form id="editCelebrateForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">校园节庆标题

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height:50px;width: 300px" name="celebrateTitle" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th width="120"><label class="control-label required-mark" style="width:110px"
                                       required="required">校园节庆内容

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height: 200px;width: 300px" name="celebrateDetails" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

        </table>

        <div style="text-align:center;padding:10px 5px">
            <a id="pictureCelebrateUploadBtnEdit" href="javascript:void(0);" iconCls="l-btn-icon icon-add"
               class="easyui-linkbutton"
               style="margin-right:20px;">
                图片上传
            </a>

        </div>

        <div width="200" id="editPicture"></div>
    </form>

    <div id="editCelebrateWinFooter" style="text-align:center;padding:5px">
        <a id="editCelebrateWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="editCelebrateWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>

<!--图片上传窗口-->
<div id="pictureCelebrateWin">
    <div data-options="region:'north',collapsible:false">

        <form id='pictureCelebrateForm' enctype="multipart/form-data" class="control-form">
            <div style="padding:20px">
                <input type="file" name='file' multiple accept=".jpg"/>
            </div>
        </form>

    </div>
    <div id="pictureCelebrateWinFooter" style="text-align:center;padding:5px">
        <a id="pictureCelebrateWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="pictureCelebrateWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>
</body>
</html>
