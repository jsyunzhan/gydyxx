$(function () {

    var selectedEmail, reqObj = {};
    var $emailGrid = $('#emailGrid').datagrid({
        url: path+ '/home/emailmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'sendName', title: "姓名", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'sendEmail', title: "邮箱", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'sendNumber', title: "号码", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'sendDetails', title: "留言内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "查看详情", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedEmail) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $detailsEmailForm.form('load', selectedEmail);

                        $detailsEmailWin.window('open');
                    }

                }
            }
        ],
        onBeforeLoad: function (param) {
            getPage(param);
            $.extend(param, reqObj);
        },
        onSelect: function (index, row) {
            selectedEmail = row;
        },
        onLoadSuccess: function () {
            selectedEmail = $emailGrid.datagrid('getSelected');
        }
    });

    /*************查看详情*******************/

    var $detailsEmailForm = $('#detailsEmailForm').form({
        novalidate: true
    });

    var $detailsEmailWin = $('#detailsEmailWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#detailsEmailWinFooter',
        onClose: function () {

            $('#detailsEmailForm').form('disableValidation').form('reset');
        }
    });


    $('#detailsEmailWinCloseBtn').linkbutton({
        onClick: function () {
            $detailsEmailWin.window('close');
        }
    });

});