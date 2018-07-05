$(function () {

    var selectedProfile, reqObj = {};
    var $profileGrid = $('#profileGrid').datagrid({
        url: path+ '/home/profilemanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'profileTitle', title: "学校概况标题", width: 150, sortable: true,
                align: 'left'
            },
            {
                field: 'profileDetails', title: "学校概况内容", width: 400, sortable: true,
                align: 'left'
            }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addProfileWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedProfile) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editProfileForm.form('load', selectedProfile);

                        $editProfileWin.window('open');
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
            selectedProfile = row;

        },
        onLoadSuccess: function () {
            selectedProfile = $profileGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var $addProfileForm = $('#addProfileForm').form({
        novalidate: true
    });

    var $addProfileWin = $('#addProfileWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addProfileWinFooter',
        onClose: function () {
            $('#addProfileForm').form('disableValidation').form('reset');
        }
    });

    $('#addProfileWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addWorksForm').form('enableValidation').form('validate')) {
                return;
            }

            var profileData = $addProfileForm.serializeObject(),
                url = path + "/home/profilemanpage/add";

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(profileData),
                success:function (r) {
                    $profileGrid.datagrid('reload');
                    $addProfileWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })
        }
    });

    $('#addProfileWinCloseBtn').linkbutton({
        onClick: function () {
            $addProfileWin.window('close');
        }
    });

    /*************修改*******************/

    var $editProfileForm = $('#editProfileForm').form({
        novalidate: true
    });

    var $editProfileWin = $('#editProfileWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editProfileWinFooter',
        onClose: function () {
            $('#editProfileForm').form('disableValidation').form('reset');
        }
    });

    $('#editProfileWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editProfileForm').form('enableValidation').form('validate')) {
                return;
            }

            var profileData = $editProfileForm.serializeObject(),
                url = path + "/home/profilemanpage/edit";
            profileData.id = selectedProfile.id;


            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(profileData),
                success:function (r) {
                    $profileGrid.datagrid('reload');
                    $editProfileWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })



        }
    });

    $('#editProfileWinCloseBtn').linkbutton({
        onClick: function () {
            $editProfileWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedProfile) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除学校概况：<span style='color: red;'>{0}</span>？", selectedProfile.profileTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/profilemanpage/delete/"+selectedProfile.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $profileGrid.datagrid('reload');
                }
            })
        })
    }

});