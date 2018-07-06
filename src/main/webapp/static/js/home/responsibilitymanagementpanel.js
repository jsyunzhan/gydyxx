$(function () {

    var selectedResponsibility, reqObj = {};
    var $responsibilityGrid = $('#responsibilityGrid').datagrid({
        url: path+ '/home/responsibilitymanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'responsibilityTitle', title: "责任督学标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'responsibilityDetails', title: "责任督学内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addResponsibilityWin   .window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedResponsibility) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editResponsibilityForm.form('load', selectedResponsibility);

                        $editResponsibilityWin.window('open');
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
            selectedResponsibility = row;

        },
        onLoadSuccess: function () {
            selectedResponsibility = $responsibilityGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addResponsibilityForm = $('#addResponsibilityForm').form({
        novalidate: true
    });

    var $addResponsibilityWin = $('#addResponsibilityWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addResponsibilityWinFooter',
        onClose: function () {
            $('#addResponsibilityForm').form('disableValidation').form('reset');
        }
    });

    $('#addResponsibilityWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addWorksForm').form('enableValidation').form('validate')) {
                return;
            }

            var responsibilityData = $addResponsibilityForm.serializeObject(),
                url = path + "/home/responsibilitymanpage/add";

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(responsibilityData),
                success:function (r) {
                    $responsibilityGrid.datagrid('reload');
                    $addResponsibilityWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })
        }
    });

    $('#addResponsibilityWinCloseBtn').linkbutton({
        onClick: function () {
            $addResponsibilityWin.window('close');
        }
    });

    /*************修改*******************/

    var $editResponsibilityForm = $('#editResponsibilityForm').form({
        novalidate: true
    });

    var $editResponsibilityWin = $('#editResponsibilityWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#editResponsibilityWinFooter',
        onClose: function () {
            $('#editResponsibilityForm').form('disableValidation').form('reset');
        }
    });

    $('#editResponsibilityWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editResponsibilityForm').form('enableValidation').form('validate')) {
                return;
            }

            var responsibilityData = $editResponsibilityForm.serializeObject(),
                url = path + "/home/responsibilitymanpage/edit";
            responsibilityData.id = selectedResponsibility.id;

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(responsibilityData),
                success:function (r) {
                    $responsibilityGrid.datagrid('reload');
                    $editResponsibilityWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editResponsibilityWinCloseBtn').linkbutton({
        onClick: function () {
            $editResponsibilityWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedResponsibility) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除责任督学：<span style='color: red;'>{0}</span>？", selectedResponsibility.responsibilityTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/responsibilitymanpage/delete/"+selectedResponsibility.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $responsibilityGrid.datagrid('reload');
                }
            })
        })
    }

});