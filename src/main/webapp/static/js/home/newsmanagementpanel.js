$(function () {

    var selectedNews, reqObj = {};
    var $newsGrid = $('#newsGrid').datagrid({
        url: path+ '/home/newsmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'newsTitle', title: "新闻标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'newsDetails', title: "新闻内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addNoticeWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedNotice) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editNoticeForm.form('load', selectedNotice);
                        $editNoticeWin.window('open');
                    }

                }
            },
            {
                text: "删除", iconCls: 'icon-remove',
                handler: function () {
                    removeHandle();
                }
            }
        ],
        onBeforeLoad: function (param) {
            getPage(param);
            $.extend(param, reqObj);
        },
        onSelect: function (index, row) {
            selectedNews = row;

        },
        onLoadSuccess: function () {
            selectedNews = $newsGrid.datagrid('getSelected');
        }
    });

});