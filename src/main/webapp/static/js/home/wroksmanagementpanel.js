$(function () {

    var selectedWorks, reqObj = {};
    var $worksGrid = $('#worksGrid').datagrid({
        url: path+ '/home/worksmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'worksTitle', title: "作品标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'worksDetails', title: "作品内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addWorksWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedNews) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editNewsForm.form('load', selectedNews);
                        $editNewsWin.window('open');
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
            selectedWorks = row;

        },
        onLoadSuccess: function () {
            selectedWorks = $worksGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addWorksForm = $('#addWorksForm').form({
        novalidate: true
    });

    var $addWorksWin = $('#addWorksWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addWorksWinFooter',
        onClose: function () {
            $('#addWorksForm').form('disableValidation').form('reset');
        }
    });

    $('#addWorksWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addWorksForm').form('enableValidation').form('validate')) {
                return;
            }

            var worksData = $addWorksForm.serializeObject(),
                url = path + "/home/worksmanpage/add";


            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(worksData),
                success:function (r) {
                    $worksGrid.datagrid('reload');
                    $addWorksWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addWorksWinCloseBtn').linkbutton({
        onClick: function () {
            $addWorksWin.window('close');
        }
    });

});