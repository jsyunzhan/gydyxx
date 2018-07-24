$(function () {

    var selectedTeaching, reqObj = {};
    var $teachingGrid = $('#teachingGrid').datagrid({
        url: path+ '/home/teachingmanpage/list', method: 'GET',
        rownumbers: true, animate: false, collapsible: true, idField: 'id', fit: true, striped: true,
        singleSelect: true,
        border: false,
        remoteSort: false,
        pagination: true,
        pageSize: 10,
        pageList: [10, 50, 100, 150],
        columns: [[
            {
                field: 'teachingTitle', title: "教学资源标题", width: 150, sortable: true,
                align: 'left'
            }
            // ,
            // {
            //     field: 'teachingDetails', title: "教学资源内容", width: 400, sortable: true,
            //     align: 'left'
            // }
        ]],
        toolbar: [
            {
                text: "新增", iconCls: 'icon-add',
                handler: function () {
                    $addTeachingWin.window('open');
                }
            },
            {
                text: "修改", iconCls: 'icon-edit',
                handler: function () {

                    if (!selectedTeaching) {
                        showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
                    } else {
                        $editTeachingForm.form('load', selectedTeaching);

                        $(document).ready(function(){
                            var ue = UE.getEditor('containerEdit');
                            ue.ready(function() {//编辑器初始化完成再赋值
                                ue.setContent(selectedTeaching.teachingDetails);  //赋值给UEditor
                            });
                        });

                        $editTeachingWin.window('open');
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
            selectedTeaching = row;
        },
        onLoadSuccess: function () {
            selectedTeaching = $teachingGrid.datagrid('getSelected');
        }
    });

    /*************新增*******************/

    var reportAdd = UE.getEditor('containerAdd', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $addTeachingForm = $('#addTeachingForm').form({
        novalidate: true
    });

    var $addTeachingWin = $('#addTeachingWin').window({
        title: "新增", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-add', collapsible: false, minimizable: false,
        footer: '#addTeachingWinFooter',
        onClose: function () {
            $('#addTeachingWinFooter').form('disableValidation').form('reset');
            reportAdd.setContent("")
        }
    });

    $('#addTeachingWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#addTeachingForm').form('enableValidation').form('validate')) {
                return;
            }

            var teachingData = $addTeachingForm.serializeObject(),
                url = path + "/home/teachingmanpage/add";

            if (!teachingData.teachingDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(teachingData),
                success:function (r) {
                    $teachingGrid.datagrid('reload');
                    $addTeachingWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#addTeachingWinCloseBtn').linkbutton({
        onClick: function () {
            $addTeachingWin.window('close');
        }
    });

    /*************修改*******************/

    var reportEdit = UE.getEditor('containerEdit', {
        initialFrameWidth: '100%', initialFrameHeight: 240
    });

    var $editTeachingForm = $('#editTeachingForm').form({
        novalidate: true
    });

    var $editTeachingWin = $('#editTeachingWin').window({
        title: "修改", closed: true, modal: true, height: 380,
        width: 600, iconCls: 'icon-edit', collapsible: false, minimizable: false,
        footer: '#editTeachingWinFooter',
        onClose: function () {
            reportEdit.setContent("");
            $('#editTeachingForm').form('disableValidation').form('reset');
        }
    });

    $('#editTeachingWinSubmitBtn').linkbutton({
        onClick: function () {
            if (!$('#editTeachingForm').form('enableValidation').form('validate')) {
                return;
            }

            var teachingData = $editTeachingForm.serializeObject(),
                url = path + "/home/teachingmanpage/edit";
            teachingData.id = selectedTeaching.id;

            if (!teachingData.teachingDetails){
                showErrorMessage("正文不可为空！");
                return
            }

            $.ajax({
                url:url,type:"POST",contentType: "application/json",data:JSON.stringify(teachingData),
                success:function (r) {
                    $teachingGrid.datagrid('reload');
                    $editTeachingWin.window('close');
                    showInfoMessage(SYSTEM_MESSAGE.msg_action_success)
                }
            })

        }
    });

    $('#editTeachingWinCloseBtn').linkbutton({
        onClick: function () {
            $editTeachingWin.window('close');
        }
    });

    /************删除*************/

    function removeHandle() {
        if (!selectedTeaching) {
            showWarningMessage(SYSTEM_MESSAGE.msg_please_select_record);
            return
        }



        var msg = String.format("您确定要删除教学资源：<span style='color: red;'>{0}</span>？", selectedTeaching.teachingTitle);

        showConfirm(msg, function () {
            $.ajax({
                url:path + "/home/teachingmanpage/delete/"+selectedTeaching.id,
                type:"GET",dataType:"json",
                success:function (r) {
                    $teachingGrid.datagrid('reload');
                }
            })
        })
    }

});