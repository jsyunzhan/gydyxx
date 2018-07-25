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
            }
            , {
                field: 'statueId', title: "是否开启", width: 150, sortable: true,
                align: 'left',formatter:result
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



                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedProfile.profileDetails);  //赋值给UEditor
                            });
                        });

                        $editProfileWin.window('open');
                    }

                }
            },
            {
                text: "删除", iconCls: 'icon-remove',
                handler: function () {
                    removeHandle();
                }
            }, {
                text: "启用", iconCls: 'icon-remove',
                handler: function () {
                    open();
                }
            },
            {
                text: "禁用", iconCls: 'icon-remove',
                handler: function () {
                    close();
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

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });


    var $addProfileForm = $('#addProfileForm').form({
        novalidate: true
    });

    var $addProfileWin = $('#addProfileWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addProfileWinFooter',
        onClose: function () {
            $('#addProfileForm').form('disableValidation').form('reset');
            reportAdd.setContent('');
        }
    });

    $('#addProfileWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addProfileForm').form('enableValidation').form('validate')) {
                return;
            }

            var profileData = $addProfileForm.serializeObject(),
                url = path + "/home/profilemanpage/add";

            if (!profileData.profileDetails){
                showErrorMessage("正文不可为空！");
                return
            }

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

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editProfileForm = $('#editProfileForm').form({
        novalidate: true
    });

    var $editProfileWin = $('#editProfileWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editProfileWinFooter',
        onClose: function () {
            $('#editProfileForm').form('disableValidation').form('reset');
            reportEdit.setContent("")
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

            if (!profileData.profileDetails){
                showErrorMessage("正文不可为空！");
                return
            }


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

    /************启用*************/

    function open() {
        if (!selectedProfile) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (1 == selectedProfile.statueId){
            showWarningMessage("该条目处于开启状态，请重新选择！");
            return
        }

        var msg = String.format("您确定要启用学校概况：<span style='color: red;'>{0}</span>？", selectedProfile.profileTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/profilemanpage/open/"+selectedProfile.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $profileGrid.datagrid('reload');
                }
            })
        })
    }

    /************关闭*************/

    function close() {
        if (!selectedProfile) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }

        if (0 == selectedProfile.statueId){
            showWarningMessage("该条目处于禁用状态，请重新选择！");
            return
        }


        var msg = String.format("您确定要禁用学校概况：<span style='color: red;'>{0}</span>？", selectedProfile.profileTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/profilemanpage/close/"+selectedProfile.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $profileGrid.datagrid('reload');
                }
            })
        })
    }

    function result(value, row, index) {

        if (0 == value ) {
            return "<span style='color:red;'>" + '否' + "</span>";
        } else {
            return "<span style='color:blue;'>" + '是' + "</span>";
        }
    }

});