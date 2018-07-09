$(function () {

    var selectedLaw, reqObj = {};
    var $LawGrid = $('#LawGrid').datagrid({
        url: path+ '/home/lawmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'lawTitle', title: "法制校园标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'lawDetails', title: "法制校园内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addLawWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedLaw) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editLawForm.form('load', selectedLaw);

                        $editLawWin.window('open');
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
            selectedLaw = row;
        },
        onLoadSuccess: function () {
            selectedLaw = $LawGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addLawForm = $('#addLawForm').form({
        novalidate: true
    });

    var $addLawWin = $('#addLawWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addLawWinFooter',
        onClose: function () {
            $('#addLawForm').form('disableValidation').form('reset');
        }
    });

    $('#addLawWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addLawForm').form('enableValidation').form('validate')) {
                return;
            }

            var lawData = $addLawForm.serializeObject(),
                url = path + "/home/lawmanpage/add";

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(lawData),
                success:function (r) {
                    $LawGrid.datagrid('reload');
                    $addLawWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addLawWinCloseBtn').linkbutton({
        onClick: function () {
            $addLawWin.window('close');
        }
    });

    /*************修改*******************/

    var $editLawForm = $('#editLawForm').form({
        novalidate: true
    });

    var $editLawWin = $('#editLawWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editLawWinFooter',
        onClose: function () {

            $('#editLawForm').form('disableValidation').form('reset');
        }
    });

    $('#editLawWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editLawForm').form('enableValidation').form('validate')) {
                return;
            }

            var lawData = $editLawForm.serializeObject(),
                url = path + "/home/lawmanpage/edit";
            lawData.id = selectedLaw.id;

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(lawData),
                success:function (r) {
                    $LawGrid.datagrid('reload');
                    $editLawWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editLawWinCloseBtn').linkbutton({
        onClick: function () {
            $editLawWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedLaw) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除法制校园：<span style='color: red;'>{0}</span>？", selectedLaw.lawTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/lawmanpage/delete/"+selectedLaw.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $LawGrid.datagrid('reload');
                }
            })
        })
    }

});