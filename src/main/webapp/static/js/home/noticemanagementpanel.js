$(function () {

    var selectedNotice, reqObj = {};
    var $noticeGrid = $('#noticeGrid').datagrid({
        url: path+ '/home/noticemanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'noticeTitle', title: "公告标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'noticeDetails', title: "公告内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addMessageWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedPhoneMess) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editMessageForm.form('load', selectedPhoneMess);
                        $editMessageWin.window('open');
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
            selectedNotice = row;

        },
        onLoadSuccess: function () {
            selectedNotice = $noticeGrid.datagrid('getSelected');
        }
    });

});