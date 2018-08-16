<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/26
  Time: 10:38
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
            src="<c:url value="/static/js/wages/wagesmanagementpanel.js"/>"></script>
</head>
<body class="easyui-layout">


<div data-options="region:'center'">
    <table id="wagesGrid"></table>
</div>

<!--新增窗口-->
<div id="addWagesWin">
    <form id="addWagesForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:110px"
                                       required="required">工资名称

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" name="wagesName" required="required"
                                       data-options="prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th><label class="control-label required-mark" style="width:80px">日期</label>
                </th>
                <td><input class="easyui-datebox" name="wagesDate" required="required" data-options="prompt:'请选择...'"/></td>
            </tr>

        </table>

        <div style="text-align:center;padding:10px 5px">
            <a id="wagesExcelBtn" href="javascript:void(0);" iconCls="l-btn-icon icon-add"
               class="easyui-linkbutton"
               style="margin-right:20px;">
                表格上传
            </a>

        </div>

    </form>

    <div id="addWagesWinFooter" style="text-align:center;padding:5px">
        <a id="addWagesWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="addWagesWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>

<!--修改窗口-->
<div id="editWagesWin">
    <form id="editWagesForm" class="control-form">
        <table class="table_list" style="text-align: right;margin:3px;width:99%">
            <tr>
                <th width="120"><label class="control-label required-mark" style="width:110px"
                                       required="required">工资名称

                </label>
                </th>
                <td width="200"><input class="easyui-textbox control" name="wagesName" required="required"
                                       data-options="prompt:'请输入...'"/></td>
            </tr>

            <tr>
                <th><label class="control-label required-mark" style="width:80px">日期</label>
                </th>
                <td><input class="easyui-datebox" name="wagesDate" required="required"/></td>
            </tr>

        </table>

        <div style="text-align:center;padding:10px 5px">
            <a id="wagesExcelBtnEdit" href="javascript:void(0);" iconCls="l-btn-icon icon-add"
               class="easyui-linkbutton"
               style="margin-right:20px;">
                表格上传
            </a>

        </div>

    </form>

    <div id="editWagesWinFooter" style="text-align:center;padding:5px">
        <a id="editWagesWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="editWagesWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>

<!--查看详情窗口-->
<div id="detailsWagesWin">
    <style>
        #detailsWagesWin{
            width: 100%;
            overflow-x: auto;
        }
        .pay{
            white-space: nowrap;
            font-size: 0;
        }
        .pay .payNum{
            width: 100px;
            line-height: 50px;
            font-size: 18px;
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

    <div id="detailsWinFooter" style="text-align:center;padding:5px">

        <a id="detailsWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            关闭
        </a>
    </div>
</div>

<!--表格上传窗口-->
<div id="wagesExcelWin">
    <div data-options="region:'north',collapsible:false">

        <form id='wagesExcelForm' enctype="multipart/form-data" class="control-form">
            <div style="padding:20px">
                <input type="file" name='file' accept=".xlsx"/>
            </div>
        </form>

    </div>
    <div id="wagesExcelWinFooter" style="text-align:center;padding:5px">
        <a id="wagesExcelWinSubmitBtn" href="javascript:void(0);" iconCls="icon-ok" class="easyui-linkbutton"
           style="margin-right:20px;">
            确认
        </a>
        <a id="wagesExcelWinCloseBtn" href="javascript:void(0);" iconCls="icon-cancel" style="margin-left:20px;"
           class="easyui-linkbutton">
            取消
        </a>
    </div>
</div>
</body>
</html>
