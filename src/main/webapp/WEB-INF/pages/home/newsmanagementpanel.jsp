<%--
  Created by IntelliJ IDEA.
  User: new
  Date: 2018/6/28
  Time: 10:10
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
            src="<c:url value="/static/js/home/newsmanagementpanel.js"/>"></script>
</head>
<body class="easyui-layout">


<div data-options="region:'center'">
    <table id="newsGrid"></table>
</div>

<!--新增窗口-->
<div id="addNewsWin">
    <form id="addNewsForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">新闻标题

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height:50px;width: 300px" name="newsTitle" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>


        </table>

        <div style="text-align:center;padding:10px 5px">
            <a id="pictureNewsUploadBtnAdd" href="javascript:void(0);" iconCls="l-btn-icon icon-add"
               class="easyui-linkbutton"
               style="margin-right:20px;">
                图片上传
            </a>

        </div>

        <div style="padding-top: 2px; margin-right: 2px">
            <script id="containerAdd" name="newsDetails" type="text/plain"></script>
        </div>



        <div width="200" id="addPicture"></div>
    </form>

    <div id="addNewsWinFooter" style="text-align:center;padding:5px">
        <a id="addNewsWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="addNewsWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>

<!--修改窗口-->
<div id="editNewsWin">
    <form id="editNewsForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:100px"
                                       required="required">新闻标题

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" style="height:50px;width: 300px" name="newsTitle" required="required"
                                       data-options="multiline:true,prompt:'请输入...'"/></td>
            </tr>

        </table>

        <div style="text-align:center;padding:10px 5px">
            <a id="pictureNewsUploadBtnEdit" href="javascript:void(0);" iconCls="l-btn-icon icon-add"
               class="easyui-linkbutton"
               style="margin-right:20px;">
                图片上传
            </a>

        </div>

        <div style="padding-top: 2px; margin-right: 2px">
            <script id="containerEdit" name="newsDetails" type="text/plain"></script>
        </div>



        <div width="200" id="editPicture"></div>
    </form>

    <div id="editNewsWinFooter" style="text-align:center;padding:5px">
        <a id="editNewsWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="editNewsWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>

<!--图片上传窗口-->
<div id="pictureNewsWin">
    <div data-options="region:'north',collapsible:false">

        <form id='pictureNewsForm' enctype="multipart/form-data" class="control-form">
            <div style="padding:20px">
                <input type="file" name='file' multiple accept=".jpg"/>
            </div>
        </form>

    </div>
    <div id="pictureNewsWinFooter" style="text-align:center;padding:5px">
        <a id="pictureNewsWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="pictureNewsWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>
</body>

</html>
