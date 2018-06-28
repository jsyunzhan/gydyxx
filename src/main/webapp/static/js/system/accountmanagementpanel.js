$(function () {

    /*****************下拉框加载************************/
    var $addRoleId = $('#addRoleId,#editRoleId').combobox({
        panelHeight: 'auto', valueField: 'id',
        textField: 'roleName', editable: false
    });

    $.ajax({
        url:path + "/system/accountmanpage/getAllRole",
        type:"GET",dataType:"json",
        success:function (r) {
            $addRoleId.combobox('loadData',r);
        }
    })

    var selectedaccount, reqObj = {};
    var $accountGrid = $('#accountGrid').datagrid({
        url: path + '/system/accountmanpage/accountlist', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'userName', title: "用户名", width: 100, sortable: true,
                align: 'left'
            },
            {
                field: 'loginName', title: "登录名", width: 100, sortable: true,
                align: 'left'
            },
            {
                field: 'roleName', title: "角色名称", width: 100, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addAccountWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedaccount) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editAccountForm.form('load', selectedaccount);
                        $editAccountWin.window('open');
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
            selectedaccount = row;

        },
        onLoadSuccess: function () {
            selectedaccount = $accountGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    $('#confirmPassword').textbox({
        validType: 'passwordMatch["#password"]'
    });

    var $addAccountForm = $('#addAccountForm').form({
        novalidate: true
    });

    var $addAccountWin = $('#addAccountWin').window({
        title: "新增", closed: true, modal: true, height: 260,
        width: 360, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addAccountWinFooter',
        onClose: function () {
            $('#addAccountForm').form('disableValidation').form('reset');
        }
    });

    $('#addAccountWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addAccountForm').form('enableValidation').form('validate')) {
                return;
            }

            var accountData = $addAccountForm.serializeObject(),
                url = path + "/system/accountmanpage/add";

            var data = {loginName:accountData.loginName};
            if (!checkLoginName(data)){
                showWarningMessage("已存在的登录名，请重新输入");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(accountData),
                success:function (r) {
                    $accountGrid.datagrid('reload');
                    $addAccountWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addAccountWinCloseBtn').linkbutton({
        onClick: function () {
            $addAccountWin.window('close');
        }
    });

    /*************修改*******************/

    $('#editPassword').textbox({
        validType: 'passwordMatch["#editConfirmPassword"]'
    });

    var $editAccountForm = $('#editAccountForm').form({
        novalidate: true
    });

    var $editAccountWin = $('#editAccountWin').window({
        title: "修改", closed: true, modal: true, height: 260,
        width: 360, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editAccountWinFooter',
        onClose: function () {
            $('#editAccountForm').form('disableValidation').form('reset');
        }
    });

    $('#editAccountWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editAccountForm').form('enableValidation').form('validate')) {
                return;
            }

            var accountData = $editAccountForm.serializeObject(),
                url = path + "/system/accountmanpage/edit";

            var data = {id:selectedaccount.id,loginName:accountData.loginName};
            if (!checkLoginName(data)){
                showWarningMessage("已存在的登录名，请重新输入");
                return
            }

            accountData.id = selectedaccount.id;
            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(accountData),
                success:function (r) {
                    $accountGrid.datagrid('reload');
                    $editAccountWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editAccountWinCloseBtn').linkbutton({
        onClick: function () {
            $editAccountWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedaccount) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }


        var msg = String.format("您确定要删除用户：<span style='color: red;'>{0}</span>？", selectedaccount.userName);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/system/accountmanpage/delete/"+selectedaccount.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $accountGrid.datagrid('reload');
                }
            })
        })
    }
});